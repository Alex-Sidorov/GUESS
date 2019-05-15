import QtQuick 2.0
import QtQuick.Controls 2.0
import QtMultimedia 5.12

Page {
    id: camera_page

    signal image_capture(string preview)

    property var width_video_output
    property var height_video_output
    width: 360
    height: 615

    background: Rectangle {
        color: "black"
    }

    Camera {
        id: camera
        imageCapture {
            onImageCaptured: {
                console.log(width_video_output)
                console.log(height_video_output)
                console.log(video_output.x)
                console.log(video_output.y)
                image_capture(preview)
            }
        }
    }

    VideoOutput {
        id: video_output
        fillMode: VideoOutput.Stretch
        width: width_video_output
        height: height_video_output
        source: camera
        focus: visible
        autoOrientation: true
        Image {
            id: capture_image
            x: 12
            y: 508
            width: 97
            height: 64
            z: 0
            source: "images/cap.png"
            fillMode: Image.PreserveAspectFit
        }

        Image {
            id: focus_image
            x: 216
            y: 508
            width: 126
            height: 64
            source: "images/foc.png"
            fillMode: Image.PreserveAspectFit
        }

        Button {
            id: capture_button
            x: 12
            y: 508
            width: 97
            height: 64
            text: qsTr("")
            z: -2
            onClicked: {
                camera.imageCapture.capture()
            }
        }


        /*Button {
            id: galerey_button
            x: 127
            y: 508
            width: 97
            height: 64
            text: qsTr("")
            z: -2
            onClicked: {
                if (openG.requestPermissions())
                    openG.openGallery()
            }
        }

        Image {
            id: galerey_image
            x: 127
            y: 508
            width: 97
            height: 64
            source: "images/gal.png"
        }*/
        Button {
            id: focus_button
            x: 216
            y: 508
            width: 126
            height: 64
            text: qsTr("")
            z: -2
            onClicked: {
                camera.FocusAuto
                if (camera.lockStatus == Camera.Unlocked) {
                    camera.searchAndLock()
                } else {
                    camera.unlock()
                }
            }
        }
    }
}
