import QtQuick 2.0
import QtQuick.Controls 2.0

Page {
    id: signup_page

    signal clicked_signup

    property alias login: signup_login_input.text
    property alias password: signup_password_input.text
    property alias first_name: signup_first_name_input.text
    property alias last_name: signup_last_name_input.text

    property string text_error_1
    property string text_error_2

    property bool visible_error_1
    property bool visible_error_2
    property bool load: false

    property var width_fields

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
        id: text_login_signup
        x: width_fields / 10
        y: 11
        width: 213
        height: 28
        color: "white"
        text: qsTr("Введите данные")
        font.pixelSize: 25
    }

    TextField {
        id: signup_first_name_input
        x: width_fields / 10
        y: 54
        width: width_fields * 0.8
        height: 45
        background: Rectangle {
            color: "#1d4a03"
            border.color: "black"
        }
        color: "white"
        font.pointSize: 25
        placeholderText: "Имя"
    }

    TextField {
        id: signup_last_name_input
        x: width_fields / 10
        y: 105
        width: width_fields * 0.8
        height: 45
        background: Rectangle {
            color: "#1d4a03"
            border.color: "black"
        }
        color: "white"
        font.pointSize: 25
        placeholderText: "Фамилия"
    }

    TextField {
        id: signup_login_input
        x: width_fields / 10
        y: 156
        width: width_fields * 0.8
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
        id: signup_data_button
        x: width_fields / 10
        y: 278
        width: width_fields*0.8
        height: 38
        background: Rectangle {
            color: "white"
        }
        text: "Зарегистрироваться"
        onClicked: {
            if (signup_first_name_input.length === 0
                    || signup_last_name_input.length === 0
                    || signup_password_input.length === 0
                    || signup_login_input.length === 0) {
                signup_error_text_1.visible = true
                signup_error_text_2.visible = false
                signup_error_text_1.text = "Заполните все поля"
                return
            } else if (signup_password_input.length < 5) {
                signup_error_text_1.visible = true
                signup_error_text_2.visible = true
                signup_error_text_1.text = "Пароль должен быть"
                signup_error_text_2.text = "от пяти символов"
                return
            } else if (signup_login_input.length < 5) {
                signup_error_text_1.visible = true
                signup_error_text_2.visible = true
                signup_error_text_1.text = "Email должен быть"
                signup_error_text_2.text = "от пяти символов"
                return
            } else if (signup_first_name_input.length < 3) {
                signup_error_text_1.visible = true
                signup_error_text_2.visible = true
                signup_error_text_1.text = "Имя должно быть"
                signup_error_text_2.text = "от трех символов"
                return
            } else if (signup_last_name_input.length < 3) {
                signup_error_text_1.visible = true
                signup_error_text_2.visible = true
                signup_error_text_1.text = "Фамилия должна быть"
                signup_error_text_2.text = "от трех символов"
                return
            }
            clicked_signup()
        }
    }

    TextField {
        id: signup_password_input
        x: width_fields / 10
        y: 207
        width: width_fields * 0.8
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

    Text {
        id: signup_error_text_1
        x: width_fields / 10
        y: 485
        width: 294
        height: 29
        color: "white"
        text: text_error_1
        font.pixelSize: 20
        visible: visible_error_1
    }

    Text {
        id: signup_error_text_2
        x: width_fields / 10
        y: 509
        width: 286
        height: 13
        color: "white"
        text: text_error_2
        font.pixelSize: 20
        visible: visible_error_2
    }
    BusyIndicator {
        id: signup_load
        x: width_fields / 2
        y: 501
        running: load
    }

    function clear_field() {
        signup_login_input.clear()
        signup_password_input.clear()
        signup_first_name_input.clear()
        signup_last_name_input.clear()
    }
}
