package com.dacs.choithuephongtro.bootstrap;

import com.dacs.choithuephongtro.entities.Category;
import com.dacs.choithuephongtro.entities.Detail;
import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.repositories.CategoryRepository;
import com.dacs.choithuephongtro.repositories.DetailRepository;
import com.dacs.choithuephongtro.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final RoomRepository roomRepository;
    private final CategoryRepository categoryRepository;
    private final DetailRepository detailRepository;

    @Override
    public void run(String... args) throws Exception {
        LoadCategoryData();
        LoadRoomData();
    }

    private void LoadCategoryData() {
        if(categoryRepository.count() == 0){
            Category category1 = Category.builder()
                    .description("Phòng Trọ")
                    .build();

            Category category2 = Category.builder()
                    .description("Nhà Nguyên Căn")
                    .build();

            categoryRepository.save(category1);
            categoryRepository.save(category2);
        }
    }

    private void LoadRoomData() {
        if(roomRepository.count() <= 5){
            Category category = categoryRepository.findAllByDescriptionIsLikeIgnoreCase("%" + "Phòng Trọ" + "%");
            System.out.println(category);

            Room room1 = Room.builder()
                    .name("Phòng trọ 1")
                    .build();
            room1.setCategory(category);

            Detail detail1 = Detail.builder()
                    .area(25.f)
                    .description("Phòng ni nhiều gián")
                    .build();

            room1.setDetail(detail1);
            detail1.setRoom(room1);

            Room room2 = Room.builder()
                    .name("Phòng trọ 3")
                    .build();
            room2.setCategory(category);


            Detail detail2 = Detail.builder()
                    .area(30.f)
                    .description("Phòng ni nhiều gián")
                    .build();

            room2.setDetail(detail2);
            detail2.setRoom(room2);

            roomRepository.save(room1);
            roomRepository.save(room2);
        }
    }
}
