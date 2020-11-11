data class Chat(
    val id: Int = 0,
    val toId: Int = 0,
    val fromId: Int = 0,
    var messages: MutableList<Message> = mutableListOf<Message>()
)