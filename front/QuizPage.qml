import QtQuick 2.0
import QtQuick.Controls 2.0

Page {
    id: quiz_page

    signal clicked_answer(string answer)
    signal clicked_repeat

    property string text_answer_one
    property string text_answer_two
    property string text_answer_three
    property string text_answer_four

    property url image_source

    property var width_image
    property var height_image
    width: 360
    height: 615

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
        id: answer_one
        x: 0
        y: 466
        width: width_image / 2 - 1
        height: 45
        focusPolicy: Qt.TabFocus
        display: AbstractButton.TextOnly
        font.pointSize: 20
        text: text_answer_one
        background: Rectangle {
            color: "white"
        }
        onClicked: {
            clicked_answer(answer_one.text)
        }
    }

    Button {
        id: answer_two
        x: width_image / 2 + 1
        y: 466
        width: width_image / 2 - 1
        height: 45
        font.pointSize: 20
        display: AbstractButton.TextOnly
        text: text_answer_two
        background: Rectangle {
            color: "white"
        }
        onClicked: {
            clicked_answer(answer_two.text)
        }
    }

    Button {
        id: answer_three
        x: 0
        y: 517
        width: width_image / 2 - 1
        height: 45
        font.pointSize: 20
        display: AbstractButton.TextOnly
        text: text_answer_three
        background: Rectangle {
            color: "white"
        }
        onClicked: {
            clicked_answer(answer_three.text)
        }
    }

    Button {
        id: answer_four
        x: width_image / 2 + 1
        y: 517
        width: width_image / 2 - 1
        height: 45
        font.pointSize: 20
        display: AbstractButton.TextOnly
        text: text_answer_four
        background: Rectangle {
            color: "white"
        }
        onClicked: {
            clicked_answer(answer_four.text)
        }
    }

    Button {
        id: repeat_photo
        x: 0
        y: 568
        width: width_image
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

    Image {
        id: image
        x: 0
        y: 0
        width: width_image
        height: height_image * 0.75
        fillMode: Image.Stretch
        source: image_source
    }
}
