import java.lang.RuntimeException

class MessageNotFoundException(message: String) : RuntimeException(message)
class ChatNotFoundException(message: String) : RuntimeException(message)
class UserNotFoundException(message: String) : RuntimeException(message)

object chatService {
    var chatId = 0
    var messageId = 0
    var messageChatId = 0
    var chats = mutableListOf<Chat>()
    var messages = mutableListOf<Message>()

    fun createMessage(message: Message): Int {
        val newMessage = message.copy(id = messageId++)
        messages.add(newMessage)
        val target = chats.find { chat -> chat.messages.any { it.toId == newMessage.toId && it.fromId == newMessage.fromId } }
        if (target == null) {
            chats.add(Chat(id = chatId++, fromId = newMessage.fromId, toId = newMessage.toId, messages = mutableListOf(newMessage.copy(chatId = messageChatId++))))
        } else {
            target.messages.add(newMessage.copy(chatId = target.id))
        }
        return messages.last().id
    }

    fun editMessage(message: Message): Boolean {
        val indexOfOld = messages.indexOfFirst { it.id == message.id }
        val old = messages.getOrElse(indexOfOld) { throw MessageNotFoundException("Сообщение с указанным id '${message.id}' не найден") }
        messages[indexOfOld] = message.copy(id = old.id, toId = old.toId, fromId = old.fromId)
        return true
    }

    fun deleteMessage(message: Message): Boolean {
        val targetMessage = messages.find { it.id == message.id }
        return if (targetMessage == null) {
            throw MessageNotFoundException("Сообщение с указанным id '${message.id}' не найден")
        } else {
            messages.remove(targetMessage)
            val targetChat = chats.last { it.id == message.chatId }
            targetChat.messages.remove(message)
            chats.removeIf { it.messages.isEmpty() }
        }
    }

    fun deleteChat(chat: Chat): Boolean {
        val target = chats.find { it.id == chat.id }
        if (target == null) {
            throw ChatNotFoundException("Чат с указанным id '${chat.id}' не найдена")
        } else {
            chats.remove(target)
            messages.removeAll { it.chatId == target.id }
        }
        return true
    }

    fun getUnreadChartsCount(id: Int): Any {
        val target = chats.find { it.id == id }
        if (target == null) {
            throw UserNotFoundException("Пользователь с указанным id '$id' не найден")
        } else {
            val filteredChats = chats.filter { it.toId == id }
            if (filteredChats.isNotEmpty()) {
                val count = filteredChats.filter { chat -> chat.messages.any { !it.read } }
                return count.size
            }
            return 0
        }
    }

    fun getChats(id: Int): Any {
        val target = chats.find { it.toId == id || it.fromId == id }
        if (target == null) {
            throw UserNotFoundException("Пользователь с указанным id '$id' не найден")
        } else {
            val filteredChats = chats.filter { it.toId == id || it.fromId == id }
            return if (filteredChats.isNotEmpty()) filteredChats.filter { it.messages.isNotEmpty() } else "Сообщений нет"
        }
    }

    fun getMessages(chatId: Int, messageId: Int, count: Int): List<Message> {
        val targetChat = chats.find { it.id == chatId }
        if (targetChat == null) {
            throw ChatNotFoundException("Чат с указанным id '$chatId' не найден")
        } else {
            val targetMessages = targetChat
                    .messages.filter { it.id >= messageId }
                    .take(count)
                    .toMutableList()
            return readMessages(targetMessages)
        }
    }

    fun readMessages(messages: MutableList<Message>): List<Message> {
        for ((index, message) in messages.withIndex()) {
            if (!message.read) {
                messages[index] = message.copy(read = true)
            }
        }
        return messages
    }
}
