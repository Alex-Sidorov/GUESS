import QtQuick 2.0
import QtQuick.Controls 2.0

Page {
    id: change_password_page

    property alias old_password: change_old_password_input.text
    property alias new_password: change_new_password_input.text

    property string text_error_1
    property string text_error_2

    property bool visible_error_1: false
    property bool visible_error_2: false
    property bool visible_complete: false

    property bool load: false

    property var width_fields

    signal clicked_change

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
        id: text_change_password
        x: width_fields / 10
        y: 11
        width: width_fields * 0.8
        height: 28
        color: "white"
        text: qsTr("Введите данные")
        font.pixelSize: 25
    }

    TextField {
        id: change_old_password_input
        x: width_fields / 10
        y: 50
        width: width_fields * 0.8
        height: 45
        echoMode: "Password"
        background: Rectangle {
            color: "#1d4a03"
            border.color: "black"
        }
        color: "white"
        font.pointSize: 25
        placeholderText: "Старый пароль"
    }

    TextField {
        id: change_new_password_input
        x: width_fields / 10
        y: 100
        width: width_fields * 0.8
        height: 45
        echoMode: "Password"
        background: Rectangle {
            color: "#1d4a03"
            border.color: "black"
        }
        color: "white"
        font.pointSize: 25
        placeholderText: "Новый пароль"
    }

    Button {
        id: change_password_data_button
        x: width_fields / 10
        y: 150
        width: width_fields * 0.8
        height: 38
        background: Rectangle {
            color: "white"
        }
        text: "Изменить"
        onClicked: {
            if (change_old_password_input.length === 0
                    || change_new_password_input.length === 0) {
                change_password_error_text_1.visible = true
                change_password_error_text_2.visible = false
                change_password_error_text_1.text = "Заполните все поля"
                return
            } else if (change_new_password_input.length < 5) {
                change_password_error_text_1.visible = true
                change_password_error_text_2.visible = true
                change_password_error_text_1.text = "Пароль должен быть"
                change_password_error_text_2.text = "от пяти символов"
                return
            }
            clicked_change()
        }
    }

    BusyIndicator {
        id: change_password_load
        x: width_fields / 2
        y: 501
        running: load
    }

    Text {
        id: change_password_error_text_1
        x: width_fields / 10
        y: 485
        width: width_fields * 0.8
        height: 29
        color: "white"
        text: text_error_1
        font.pixelSize: 20
        visible: visible_error_1
    }

    Text {
        id: change_password_error_text_2
        x: width_fields / 10
        y: 509
        width: width_fields * 0.8
        height: 13
        color: "white"
        text: text_error_2
        font.pixelSize: 20
        visible: visible_error_2
    }

    Text {
        id: change_password_complete
        x: width_fields / 10
        y: 485
        width: width_fields * 0.8
        height: 29
        color: "white"
        text: "Пароль изменен"
        font.pixelSize: 20
        visible: visible_complete
    }

    function clear_field() {
        change_old_password_input.clear()
        change_new_password_input.clear()
    }
}
