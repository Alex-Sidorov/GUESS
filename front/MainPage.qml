import QtQuick 2.0
import QtQuick.Controls 2.0

Page {
    id: main_page

    signal clicked_start()

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
                color: "black"
            }
        }
    }

    Text {
        id: main_text
        x: 116
        y: 341
        color: "white"
        text: qsTr("GUESS")
        font.pixelSize: 40
    }

    Image {
        id: logo
        x: 15
        y: 7
        width: 321
        height: 343
        source: "images/logo.png"
        fillMode: Image.PreserveAspectFit

        Image {
            id: drawer_point
            x: -60
            y: 273
            width: 95
            height: 45
            rotation: 90
            source: "images/point.png"
            fillMode: Image.PreserveAspectFit
        }
    }

    MouseArea {
        id: start_mouse
        x: 15
        y: 419
        width: 321
        height: 93

        Text {
            id: text_start
            x: 122
            y: 32
            color: "#f1f0f0"
            text: qsTr("Старт")
            font.family: "Tahoma"
            renderType: Text.NativeRendering
            font.pixelSize: 25
        }
        onClicked: {
            clicked_start()
        }
    }
}
