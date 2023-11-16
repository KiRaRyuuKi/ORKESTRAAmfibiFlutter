object DataContract {
    const val ip = "192.168.204.31"

    val urlRegister: String
        get() = "http://$ip/my_api_android/api-register.php"

    val urlLogin: String
        get() = "http://$ip/my_api_android/api-login.php"
}