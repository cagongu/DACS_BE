package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.Exception.UserNotFoundException;
import com.dacs.choithuephongtro.entities.LeaseContract;
import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.repositories.ContractRepository;
import com.dacs.choithuephongtro.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
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
    private static final String path = "src/main/resources/static/room/hopdong.docx";
    private static final String path_output = "src/main/resources/static/room/hopdong1.docx";

    @Override
    @Async
    public CompletableFuture<Void> CreateContract(UUID roomId, LeaseContract leaseContract) throws IOException, UserNotFoundException {
        Room room = roomRepository.findById(roomId).orElse(null);
        assert room != null;
//            UUID ownerId = room.getRoom_owner_id();
        UUID ownerId = UUID.fromString("36f8ea87-80fb-450f-9885-be1e681e9b35");
        User owner = userService.getUserById(ownerId);
        leaseContract.setLessor(owner);
        User tenant = leaseContract.getLessee();
        User user = userService.getUserByFullName(tenant.getFullname());
        leaseContract.setLessee(user);

        contractRepository.save(leaseContract);
        room.addContract(leaseContract);
        roomRepository.save(room);

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

        try (FileInputStream fis = new FileInputStream(path);
             XWPFDocument document = new XWPFDocument(fis)) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                replaceTextInParagraph(paragraph, replacements);
            }

            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            replaceTextInParagraph(paragraph, replacements);
                        }
                    }
                }
            }
            try (FileOutputStream fos = new FileOutputStream(path_output)) {
                document.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
    }
    private static void replaceTextInParagraph(XWPFParagraph paragraph, Map<String, String> replacements) {
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
