fun main() {
    val message = Message(text = "000")
    val message1 = Message(text = "111")
    val message2 = Message(text = "222")
    val message3 = Message(text = "333")
    val message4 = Message(text = "444")
    val message5 = Message(text = "555")
    val message6 = Message(text = "666")
    val message7 = Message(text = "777")

    ChatService.createMessage(message)
    ChatService.createMessage(message1)
    ChatService.createMessage(message2)
    ChatService.createMessage(message3)
    ChatService.createMessage(message4)
    ChatService.createMessage(message5)
    ChatService.createMessage(message6)
    ChatService.createMessage(message7)

    ChatService.chats.forEach { println(it) }
    println()

    println(ChatService.getMessages(0, 2, 3))
}