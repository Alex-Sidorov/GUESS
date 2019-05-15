import QtQuick 2.0
import QtQuick.Controls 2.0


Page {
    id: profile_page

    property bool load: false
    property bool visible_info: false

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
        id: text_wait_info
        x: 43
        y: 36
        visible: profile_load.running
        color: "white"
        text: qsTr("Подождите...")
        font.pixelSize: 25
    }

    Text {
        id: text_first_name_profile
        x: 45
        y: 124
        width: 263
        height: 28
        color: "white"
        text: qsTr("Имя:" + profile.first_name)
        font.pixelSize: 25
        visible: visible_info
    }

    Text {
        id: text_last_name_profile
        x: 45
        y: 175
        width: 263
        height: 28
        color: "white"
        text: qsTr("Фамилия:" + profile.last_name)
        font.pixelSize: 25
        visible: visible_info
    }

    Text {
        id: text_email_profile
        x: 45
        y: 226
        width: 263
        height: 28
        color: "white"
        text: qsTr("Email:" + profile.email)
        font.pixelSize: 25
        visible: visible_info
    }

    BusyIndicator {
        id: profile_load
        x: 147
        y: 501
        running: load
    }
}
