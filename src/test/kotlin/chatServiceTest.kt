import junit.framework.Assert.assertEquals
import org.junit.Test

internal class chatServiceTest {

    @Test
    fun createMessage() {
        chatService.createMessage(Message())
        chatService.createMessage(Message())
    }

    @Test(expected = MessageNotFoundException::class)
    fun shouldThrowMessageNotFoundException() {
        chatService.editMessage(Message(id = 7))
    }

    @Test
    fun editMessage() {
        chatService.createMessage(Message())
        chatService.editMessage(Message())
    }

    @Test(expected = MessageNotFoundException::class)
    fun shouldThrowException() {
        chatService.deleteMessage(Message(id = 7))
    }

    @Test
    fun deleteMessage() {
        chatService.deleteMessage(Message())
    }

    @Test(expected = ChatNotFoundException::class)
    fun shouldThrowChatNotFoundException() {
        chatService.deleteChat(Chat(id = 7))
    }

    @Test
    fun deleteChat() {
        chatService.deleteChat(Chat())
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThrowUserNotFoundException() {
        chatService.getUnreadChartsCount(7)
    }

    @Test
    fun getUnreadChartsCount() {
        chatService.getUnreadChartsCount(0)
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThrow() {
        chatService.getChats(7)
    }

    @Test
    fun getChats() {
        chatService.createMessage(Message())
        chatService.getChats(0)
    }

    @Test(expected = ChatNotFoundException::class)
    fun shouldThrowChatNotFound() {
        chatService.getMessages(7, 3, 3)
    }

    @Test
    fun getMessages() {
        chatService.createMessage(Message())
        chatService.getMessages(0, 0, 1)
    }

    @Test
    fun readMessages() {
        val messages = mutableListOf<Message>(Message())
        chatService.readMessages(messages)
    }
}