import QtQuick 2.0
import QtQuick.Controls 2.0

Page {
    id: signin_page

    property alias login: signin_login_input.text
    property alias password: signin_password_input.text

    property string text_error_1
    property string text_error_2

    property bool visible_error_1
    property bool visible_error_2
    property bool load: false

    signal clicked_input(bool is_empty)

    background: Rectangle {
        gradient: Gradient {
            GradientStop {
                position: 0.0
                color: "green"
            }
            GradientStop {
                position: 0.33
                color: "#008100"
            }
            GradientStop {
                position: 1.0
                color: "#000000"
            }
        }
    }

    Text {
        id: text_login_signin
        x: 45
        y: 124
        width: 263
        height: 28
        color: "white"
        text: qsTr("Введите данные")
        font.pixelSize: 25
    }

    TextField {
        id: signin_login_input
        x: 45
        y: 195
        width: 263
        height: 45
        background: Rectangle {
            color: "#1d4a03"
            border.color: "black"
        }
        color: "white"
        font.pointSize: 25
        placeholderText: "Логин"
    }

    Button {
        id: signin_data_button
        x: 45
        y: 348
        width: 263
        height: 38
        background: Rectangle {
            color: "white"
        }
        text: "Войти"
        onClicked: clicked_input(signin_login_input.length === 0
                                 || signin_password_input.length === 0)
    }

    TextField {
        id: signin_password_input
        x: 45
        y: 253
        width: 263
        height: 45
        background: Rectangle {
            color: "#1d4a03"
            border.color: "black"
        }
        color: "white"
        font.pointSize: 25
        echoMode: "Password"
        placeholderText: "Пароль"
    }

    BusyIndicator {
        id: signin_load
        x: 147
        y: 501
        running: load
    }

    Text {
        id: signin_error_text_1
        x: 30
        y: 485
        width: 294
        height: 29
        color: "white"
        text: text_error_1
        font.pixelSize: 20
        visible: visible_error_1
    }

    Text {
        id: signin_error_text_2
        x: 32
        y: 509
        width: 286
        height: 13
        color: "white"
        text: text_error_2
        font.pixelSize: 20
        visible: visible_error_2
    }

    function clear_field() {
        signin_login_input.clear()
        signin_password_input.clear()
    }
}
