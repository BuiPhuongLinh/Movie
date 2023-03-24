package com.btplinh.domain.basenetwork

enum class MessageError(value: String) {
    SOCKET_TIME_OUT_EXCEPTION("Request timed out while trying to connect. Please ensure you have a strong signal and try again."),
    UNKNOWN_NETWORK_EXCEPTION("An unexpected error has occurred. Please check your network connection and try again."),
    CONNECT_EXCEPTION("Could not connect to the server. Please check your internet connection and try again."),
    UNKNOWN_HOST_EXCEPTION("Couldn't connect to the server at the moment. Please try again in a few minutes.")

}