import QtQuick 2.0
import QtQuick.Controls 2.0

Page {
    id: change_name_page

    property alias first_name: change_first_name_input.text
    property alias last_name: change_last_name_input.text

    property string text_error_1
    property string text_error_2

    property bool visible_error_1: false
    property bool visible_error_2: false
    property bool visible_complete: false

    property bool load: false

    property var width_fields

    signal clicked_change()

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
        id: text_change_name
        x: width_fields / 10
        y: 11
        width: 263
        height: 28
        color: "white"
        text: qsTr("Введите данные")
        font.pixelSize: 25
    }

    TextField {
        id: change_first_name_input
        x: width_fields / 10
        y: 50
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

    Button {
        id: change_name_data_button
        x: width_fields / 10
        y: 150
        width: width_fields * 0.8
        height: 38
        background: Rectangle {
            color: "white"
        }
        text: "Изменить"
        onClicked: {
            if (change_first_name_input.length === 0
                    || change_last_name_input.length === 0) {
                change_name_error_text_1.visible = true
                change_name_error_text_2.visible = false
                change_name_error_text_1.text = "Заполните все поля"
                return
            }
            clicked_change()
        }
    }

    TextField {
        id: change_last_name_input
        x: width_fields / 10
        y: 100
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

    BusyIndicator {
        id: change_name_load
        x: width_fields / 2
        y: 501
        running: load
    }

    Text {
        id: change_name_error_text_1
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
        id: change_name_error_text_2
        x: width_fields / 10
        y: 509
        width: 286
        height: 13
        color: "white"
        text: text_error_2
        font.pixelSize: 20
        visible: visible_error_2
    }

    Text {
        id: change_name_complete
        x: width_fields / 10
        y: 485
        width: 294
        height: 29
        color: "white"
        text: "Имя изменено"
        font.pixelSize: 20
        visible: visible_complete
    }

    function clear_field() {
        change_first_name_input.clear()
        change_last_name_input.clear()
    }
}
