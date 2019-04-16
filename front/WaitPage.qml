import QtQuick 2.0
import QtQuick.Controls 2.0

Page {
    id: wait_page

    property bool visible_error: false

    signal cancel_clicked

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
        id: wait_text
        x: 43
        y: 36
        color: "white"
        text: qsTr("Подождите...")
        font.pixelSize: 25
    }

    Text {
        id: error_request1
        x: 27
        y: 87
        color: "white"
        text: qsTr("Ошибка. Проверьте соединение ")
        font.pixelSize: 20
        visible: visible_error
    }

    Text {
        id: error_request2
        x: 53
        y: 116
        width: 270
        height: 22
        color: "white"
        text: qsTr("с интернетом и поробуйте")
        font.pixelSize: 20
        visible: visible_error
    }

    Text {
        id: error_request3
        x: 133
        y: 144
        width: 109
        height: 22
        color: "white"
        text: qsTr("повторить")
        font.pixelSize: 20
        visible: visible_error
    }

    Button {
        id: cancel_request
        background: Rectangle {
            color: "white"
        }
        x: 49
        y: 503
        width: 256
        height: 40
        text: "Отмена"
        font.pointSize: 15
        visible: true
        onClicked: {
            cancel_clicked()
        }
    }
}
