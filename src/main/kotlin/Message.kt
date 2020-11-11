data class Message (
    val id: Int = 0,
    val chatId: Int = 0,
    val toId: Int = 0,
    val fromId: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val read: Boolean = false
)