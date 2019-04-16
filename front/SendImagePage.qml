import QtQuick 2.0
import QtQuick.Controls 2.0

Page {
    id: send_page

    property url source_image

    signal clicked_send()
    signal clicked_repeat()

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

    Image {
        id: send_image
        anchors.top: parent.top
        anchors.topMargin: 0
        anchors.bottom: parent.bottom
        anchors.bottomMargin: 174
        anchors.right: parent.right
        anchors.rightMargin: 0
        anchors.left: parent.left
        anchors.leftMargin: 0
        fillMode: Image.PreserveAspectFit
        source: source_image
    }

    Button {
        id: send_image_button
        x: 15
        y: 460
        width: 321
        height: 40
        text: qsTr("Отправить")
        font.pointSize: 20
        background: Rectangle {
            color: "white"
        }
        onClicked:{
            clicked_send()
        }
    }

    Button {
        id: repeat_image_button
        x: 15
        y: 523
        width: 321
        height: 40
        text: qsTr("Повторить")
        font.pointSize: 20
        background: Rectangle {
            color: "white"
        }
        onClicked: {
            clicked_repeat()
        }
    }
}
