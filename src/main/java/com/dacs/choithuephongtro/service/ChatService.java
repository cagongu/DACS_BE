package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.Exception.ChatAlreadyExistException;
import com.dacs.choithuephongtro.Exception.ChatNotFoundException;
import com.dacs.choithuephongtro.Exception.NoChatExistsInTheRepository;
import com.dacs.choithuephongtro.entities.Chat;
import com.dacs.choithuephongtro.entities.Message;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public interface ChatService {
    public Chat addChat(Chat chat) throws ChatAlreadyExistException;

    List<Chat> findallchats() throws NoChatExistsInTheRepository;

    Chat getById(UUID id)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatBySecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName)  throws ChatNotFoundException;

    Chat addMessage(Message add, UUID chatId)  throws ChatNotFoundException;

    Message addMessage2(Message message);

    List<Message> getAllMessagesInChat(UUID chatId) throws NoChatExistsInTheRepository;
}
