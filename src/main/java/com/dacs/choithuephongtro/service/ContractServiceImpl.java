package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.entities.LeaseContract;
import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.repositories.ContractRepository;
import com.dacs.choithuephongtro.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final RoomRepository roomRepository;
    private final ContractRepository contractRepository;
    private final UserService userService;
    private static final String path = "E:/code/choi-thue-phong-tro/src/main/resources/static/room/hopdong.docx";
    @Override
    @Async
    public CompletableFuture<Void> CreateContract(UUID roomId, LeaseContract leaseContract) throws IOException {
        try{
            Room room = roomRepository.findById(roomId).orElse(null);
            User owner = userService.getUserById(room.getRoom_owner_id());
            User tenant = leaseContract.getLessee();
            Set<User> userList = room.getListUsers();

            for (User x : userList) {
                if(Objects.equals(x.getIdNumber(), tenant.getIdNumber())){
                    leaseContract.setLessee(x);
                    break;
                }
            }

            room.addContract(leaseContract);
            //khong luu duoc leaseContract
            contractRepository.save(leaseContract);

            InputStream templateInputStream = new FileInputStream(path);
            XWPFDocument document = new XWPFDocument(templateInputStream);

            Map<String, String> replacements = new HashMap<>();
            replacements.put("NGAYTAO", leaseContract.getCreatedDate().toString());  // Current date or pass as parameter
            replacements.put("DIACHI", leaseContract.getAddress());
            replacements.put("TEN1", owner.getFullname());
            replacements.put("NGAYSINH1", owner.getDate_of_birth().toString());
            replacements.put("DIACHI1", owner.getAddress());
            replacements.put("CCCD1", owner.getIdNumber());
            replacements.put("NGAYCAP1", owner.getIdIssueDate().toString());
            replacements.put("NOICAP1", owner.getIdIssuingAuthority());
            replacements.put("SODIENTHOAI1", owner.getPhoneNumber());
            replacements.put("TEN2", tenant.getFullname());
            replacements.put("NGAYSINH2", tenant.getDate_of_birth().toString());
            replacements.put("DIACHI2", tenant.getAddress());
            replacements.put("CCCD2", tenant.getIdNumber());
            replacements.put("NGAYCAP2", tenant.getIdIssueDate().toString());
            replacements.put("NOICAP2", tenant.getIdIssuingAuthority());
            replacements.put("SODIENTHOAI2", tenant.getPhoneNumber());
            replacements.put("GIATHUE", leaseContract.getRevenuesRent() + "");
            replacements.put("HINHTHUCTHANHTOAN", "Chuyển khoản");  // Example value
            replacements.put("TIENDIEN", leaseContract.getUnitPriceOfElectricity() + "");
            replacements.put("TIENNUOC", leaseContract.getUnitPriceOfWater() + "");
            replacements.put("TIENCOC", leaseContract.getRevenuesRent() + "");
            replacements.put("NGAYBATDAU", leaseContract.getCreatedDate().toString());
            replacements.put("NGAYKETTHUC", leaseContract.getContractDuration().toString());

            replacePlaceholders(document, replacements);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.write(outputStream);
            document.close();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            return CompletableFuture.completedFuture(null);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi tạo hợp đồng", e);
        }
    }

    private void replacePlaceholders(XWPFDocument document, Map<String, String> replacements) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null) {
                    for (Map.Entry<String, String> entry : replacements.entrySet()) {
                        if (text.contains(entry.getKey())) {
                            text = text.replace(entry.getKey(), entry.getValue());
                        }
                    }
                    run.setText(text, 0);
                }
            }
        }
    }
}
