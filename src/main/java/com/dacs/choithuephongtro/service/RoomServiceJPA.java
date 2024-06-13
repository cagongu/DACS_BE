package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.entities.Category;
import com.dacs.choithuephongtro.entities.Detail;
import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.mappers.CategoryMapper;
import com.dacs.choithuephongtro.mappers.RoomMapper;
import com.dacs.choithuephongtro.model.RoomDTO;
import com.dacs.choithuephongtro.repositories.CategoryRepository;
import com.dacs.choithuephongtro.repositories.DetailRepository;
import com.dacs.choithuephongtro.repositories.RoomRepository;
import com.dacs.choithuephongtro.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RoomServiceJPA implements RoomService {

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Override
    public Optional<RoomDTO> getRoomById(UUID id) {
        return Optional.ofNullable(roomMapper.roomToRoomDto(roomRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public Page<RoomDTO> listRooms(String roomName, Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<Room> roomPage;

        if (StringUtils.hasText(roomName)) {
            roomPage = roomRepository.findAllByNameLikeIgnoreCase("%" + roomName + "%", pageRequest);
        } else {
            roomPage = roomRepository.findAll(pageRequest);
        }

        return roomPage.map(roomMapper::roomToRoomDto);
    }

    @Override
    public Page<RoomDTO> listRoomsByCategoryId(UUID uuid, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<Room> roomPage;

        Category category = categoryRepository.findById(uuid).orElse(null);

        if (category != null) {
            roomPage = roomRepository.findByCategory(category, pageRequest);

        } else {
            roomPage = roomRepository.findAll(pageRequest);
        }
        return roomPage.map(roomMapper::roomToRoomDto);
    }

    private PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 1000) {
                queryPageSize = 1000;
            } else {
                queryPageSize = pageSize;
            }
        }

        Sort sort = Sort.by(Sort.Order.asc("name"));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

    @Override
    public RoomDTO saveNewRoom(RoomDTO roomDTO, String CategoryDescription) {
        Room room = roomMapper.roomDtoToRoom(roomDTO);
        room.setCategory(categoryRepository.findAllByDescriptionIsLikeIgnoreCase("%" + CategoryDescription + "%"));

        Detail detail = Detail.builder()
                .area(25.f)
                .description("Phòng ni nhiều gián")
                .build();

        room.setDetail(detail);
        detail.setRoom(room);

        return roomMapper.roomToRoomDto(roomRepository.save(room));
    }

    @Override
    public Optional<RoomDTO> updateRoomById(UUID id, RoomDTO room) {
        AtomicReference<Optional<RoomDTO>> atomicReference = new AtomicReference<>();

        roomRepository.findById(id).ifPresentOrElse(foundRoom -> {
            foundRoom.setName(room.getName());
            if (foundRoom.getCategory() != room.getCategory()) {
                foundRoom.setCategory(room.getCategory());
            }
            atomicReference.set(Optional.of(roomMapper.roomToRoomDto(roomRepository.save(foundRoom))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public boolean deleteRoomById(UUID id) {
        Optional<Room> room = roomRepository.findById(id);

        if (room.isPresent()) {
            detailRepository.delete(room.get().getDetail());

            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<RoomDTO> patchRoomById(UUID id, RoomDTO room) {
        AtomicReference<Optional<RoomDTO>> atomicReference = new AtomicReference<>();

        roomRepository.findById(id).ifPresentOrElse(foundRoom -> {
            if (StringUtils.hasText(room.getName())) {
                foundRoom.setName(room.getName());
            }

            if (foundRoom.getCategory() != room.getCategory()) {
                foundRoom.setCategory(room.getCategory());
            }

            atomicReference.set(Optional.of(roomMapper.roomToRoomDto(roomRepository.save(foundRoom))));
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public Optional<RoomDTO> addUserToRoom(UUID roomId, UUID userId) {
        AtomicReference<Optional<RoomDTO>> atomicReference = new AtomicReference<>();
        Optional<User> user = userRepository.findById(userId);

        user.ifPresent(value -> roomRepository.findById(roomId).ifPresentOrElse(foundRoom -> {
            foundRoom.addUser(value);

            atomicReference.set(Optional.of(roomMapper.roomToRoomDto(roomRepository.save(foundRoom))));
        }, () -> atomicReference.set(Optional.empty())));

        return atomicReference.get();
    }

    @Override
    public Optional<RoomDTO> removeUserToRoom(UUID roomId, UUID userId) {

        AtomicReference<Optional<RoomDTO>> atomicReference = new AtomicReference<>();
        Optional<User> user = userRepository.findById(userId);

        user.ifPresent(value -> roomRepository.findById(roomId).ifPresentOrElse(foundRoom -> {
            foundRoom.removeUser(value);

            atomicReference.set(Optional.of(roomMapper.roomToRoomDto(roomRepository.save(foundRoom))));
        }, () -> atomicReference.set(Optional.empty())));

        return atomicReference.get();
    }
}
