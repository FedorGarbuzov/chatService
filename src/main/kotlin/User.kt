data class User(
        val id: Int = 0,
        val chats: MutableList<Chat> = mutableListOf<Chat>()
)