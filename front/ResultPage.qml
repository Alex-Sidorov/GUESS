import QtQuick 2.0
import QtQuick.Controls 2.0

Page {
    id: result_page

    signal clicked_repeat()

    property string answer

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

    Button {
        id: repeat_after_answer
        x: 45
        y: 366
        width: 260
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

    Text {
        id: server_answer
        x: 52
        y: 64
        width: 78
        height: 14
        color: "white"
        font.pixelSize: 25
        text: answer
    }


    /*Text {
        id: our_answer
        x: 52
        y: 122
        color: "white"
        font.pixelSize: 25
    }*/
}
