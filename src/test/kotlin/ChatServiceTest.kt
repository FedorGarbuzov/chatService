import junit.framework.Assert.assertEquals
import org.junit.Test

internal class ChatServiceTest {

    @Test
    fun createMessage() {
        ChatService.createMessage(Message())
        ChatService.createMessage(Message())
    }

    @Test(expected = MessageNotFoundException::class)
    fun shouldThrowMessageNotFoundException() {
        ChatService.editMessage(Message(id = 7))
    }

    @Test
    fun editMessage() {
        ChatService.createMessage(Message())
        ChatService.editMessage(Message())
    }

    @Test(expected = MessageNotFoundException::class)
    fun shouldThrowException() {
        ChatService.deleteMessage(Message(id = 7))
    }

    @Test
    fun deleteMessage() {
        ChatService.deleteMessage(Message())
    }

    @Test(expected = ChatNotFoundException::class)
    fun shouldThrowChatNotFoundException() {
        ChatService.deleteChat(Chat(id = 7))
    }

    @Test
    fun deleteChat() {
        ChatService.deleteChat(Chat())
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThrowUserNotFoundException() {
        ChatService.getUnreadChartsCount(7)
    }

    @Test
    fun getUnreadChartsCount() {
        ChatService.getUnreadChartsCount(0)
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThrow() {
        ChatService.getChats(7)
    }

    @Test
    fun getChats() {
        ChatService.createMessage(Message())
        ChatService.getChats(0)
    }

    @Test(expected = ChatNotFoundException::class)
    fun shouldThrowChatNotFound() {
        ChatService.getMessages(7, 3, 3)
    }

    @Test
    fun getMessages() {
        ChatService.createMessage(Message())
        ChatService.getMessages(0, 0, 1)
    }
}