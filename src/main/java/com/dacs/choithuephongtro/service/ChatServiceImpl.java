package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.Exception.ChatNotFoundException;
import com.dacs.choithuephongtro.Exception.NoChatExistsInTheRepository;
import com.dacs.choithuephongtro.Exception.NotFoundException;
import com.dacs.choithuephongtro.entities.Chat;
import com.dacs.choithuephongtro.entities.Message;
import com.dacs.choithuephongtro.repositories.ChatRepository;
import com.dacs.choithuephongtro.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;

    public Chat addChat(Chat chat) {
        List<Message> messageList = chat.getMessageList();

        if(chat.getSecondUserName() == null){
            throw new NotFoundException();
        }
        else{
            if (!messageList.isEmpty()) {
                for (int i = 0; i < messageList.size(); i++) {
                    chat.getMessageList().get(i).setChat(chat);
                }
            }
            return chatRepository.save(chat);
        }
    }

    @Override
    public Chat addMessage(Message add, UUID chatId) {
        Optional<Chat> chat = chatRepository.findById(chatId);
        Chat savedChat = chat.get();
        savedChat.getMessageList().add(add);
        add.setChat(savedChat);
        return chatRepository.save(savedChat);
    }

    @Override
    public List<Message> getAllMessagesInChat(UUID chatId) throws NoChatExistsInTheRepository {
        Optional<Chat> chat = chatRepository.findById(chatId);

        if (chat.isEmpty()) {
            throw new NoChatExistsInTheRepository();
        } else {
            return chat.get().getMessageList();
        }
    }

    @Override
    public List<Chat> findallchats() throws NoChatExistsInTheRepository {
        if (chatRepository.findAll().isEmpty()) {
            throw new NoChatExistsInTheRepository();
        } else {
            return chatRepository.findAll();
        }

    }

    @Override
    public Chat getById(UUID id) throws ChatNotFoundException {
        Optional<Chat> chatid = chatRepository.findById(id);
        if (chatid.isPresent()) {
            return chatid.get();
        } else {
            throw new ChatNotFoundException();
        }
    }

    @Override
    public HashSet<Chat> getChatByFirstUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserName(username);

        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }
    }

    @Override
    public HashSet<Chat> getChatBySecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatBySecondUserName(username);
        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }
    }

    @Override
    public HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserName(username);
        HashSet<Chat> chat1 = chatRepository.getChatBySecondUserName(username);

        chat1.addAll(chat);

        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else if (chat1.isEmpty()) {
            return chat;
        } else {
            return chat1;
        }
    }

    @Override
    public HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
        HashSet<Chat> chat1 = chatRepository.getChatBySecondUserNameAndFirstUserName(firstUserName, secondUserName);
        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else if (chat.isEmpty()) {
            return chat1;
        } else {
            return chat;
        }
    }
}
