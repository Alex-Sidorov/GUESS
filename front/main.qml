import QtQuick.Window 2.1
import QtQuick 2.9
import QtQuick.Controls 2.2
import FileIO 1.0
import QtMultimedia 5.12
//import QtQuick.Dialogs 1.2

Window {
    id: window
    visible: true
    width:  350
    height: 600
    color: "#02540e"
    title: qsTr("GUESS")

    FileIO{
            id: myFile
            source: image.source
            onError: console.log(msg)
        }

    SwipeView {
        id: view
        y: 0
        width: 350
        height: 600
        clip: true
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter
        anchors.top: parent.top

        antialiasing: true
        smooth: true
        enabled: true
        //anchors.left: parent.left
        //anchors.bottom: parent.bottom
        currentIndex: 0
        interactive: false

        Page {
            id: first

            background:
                Rectangle{
                    color: "green"
                }
                Text {
                    id: main_text
                    x: 116
                    y: 338
                    color: "#bbfe1a"
                    text: qsTr("GUESS")
                    font.pixelSize: 40

                }

                Image {
                    id: image3
                    x: 15
                    y: 7
                    width: 321
                    height: 343
                    source: "images/logo.png"
                    fillMode: Image.PreserveAspectFit
                }

                Button {
                    id: start_button
                    x: 15
                    y: 411
                    width: 321
                    height: 65
                    text: qsTr("Старт")
                    font.pointSize: 25

                    display: AbstractButton.TextOnly
                    background: Rectangle {
                        color: "green"
                    }
                    onClicked: {
                        view.currentIndex = 1
                    }
                }

        }
        Page{
            id: second
            background: Rectangle{
                color: "green"
            }


            Camera {
                id: camera

                imageCapture {
                    onImageCaptured: {
                      image.source = preview
                      send_image.source = preview
                      view.currentIndex = 2
                    }
                }
            }

            MouseArea{
                anchors.fill: parent
                onClicked: {
                    camera.FocusAuto
                    camera.imageCapture.capture();
                }
            }

            VideoOutput {
                    id:video_output
                    source: camera
                    anchors.fill: parent
                    focus : visible
                    autoOrientation: true
                }

        }

        Page {
            id: third
            background: Rectangle{
                color: "green"
            }

            Image {
                id: send_image
                x: 0
                y: 86
                width: 350
                height: 242
                fillMode: Image.PreserveAspectFit
            }

            Button {
                id: send_image_button
                x: 15
                y: 396
                width: 321
                height: 40
                text: qsTr("Отправить")
                font.pointSize: 20
                background: Rectangle {
                    color: "#a8f874"
                }
                onClicked: {
                    var request = new XMLHttpRequest
                    request.open('POST', 'http://hidden-fortress-30624.herokuapp.com/api/v1/recognition')
                    request.setRequestHeader('Content-Type', 'image/jpg')
                    request.onreadystatechange = function ()
                    {
                        if (request.readyState === XMLHttpRequest.DONE)
                        {
                            if (request.status === 200)
                            {
                                var JsonObject= JSON.parse(request.responseText)
                                /*var animal = JsonObject.toString();
                                console.log(animal)
                                server_answer.text = animal*/
                                server_answer.text = request.responseText
                                view.currentIndex = 4
                                console.log(request.responseText)
                            }
                            else
                            {
                                bad_request.visible = true
                                error_request.visible = true
                                console.log("HTTP request failed", request.status/*,request.responseText*/)
                            }
                       }
                    }
                    var data = myFile.read()
                    var length = myFile.size_data()
                    request.setRequestHeader('Content-Length', length)
                    request.send(data)
                    bad_request.visible = false
                    error_request.visible = false
                    view.currentIndex = 3
                }

            }

            Button {
                id: repeat_image_button
                x: 15
                y: 447
                width: 321
                height: 40
                text: qsTr("Повторить")
                font.pointSize: 20
                background: Rectangle {
                    color: "#a8f874"
                }
                onClicked: {
                    view.currentIndex = 1
                }
            }


        }

        Page {
            id: fourth
            background: Rectangle{
                color: "green"
            }

            Text {
                id: element1
                x: 43
                y: 36
                color: "#fafa4e"
                text: qsTr("Подождите результата...")
                font.pixelSize: 25
            }

            Text {
                id: error_request
                x: 47
                y: 90
                color: "#fafa4e"
                text: qsTr("Ошибка. Поробуйте еще раз")
                font.pixelSize: 20
                visible: false
            }

            Button {
                id: bad_request
                x: 43
                y: 178
                width: 260
                height: 40
                text: qsTr("Повторить")
                font.pointSize: 20
                background: Rectangle {
                    color: "#a8f874"
                }
                onClicked: {
                    view.currentIndex = 1
                }
                visible: false
            }
        }

        Page {
            id: fiveth
            background: Rectangle{
                color: "green"
            }

            Button {
                id: answer_four
                x: 185
                y: 393
                width: 150
                height: 45
                text: qsTr("Попугай")
                font.pointSize: 20
                display: AbstractButton.TextOnly
                background: Rectangle{
                    color: "#a8f874"
                }
                //var value = false
                onClicked: {
                    our_answer.text = "Ваш ответ:"+answer_four.text
                    view.currentIndex = 5
                }
            }

            Button {
                id: answer_three
                x: 14
                y: 393
                width: 152
                height: 45
                text: qsTr("Лев")
                font.pointSize: 20
                display: AbstractButton.TextOnly
                background: Rectangle {
                    color: "#a8f874"
                }
                //var value = false
                onClicked: {
                    our_answer.text = "Ваш ответ:"+answer_three.text
                    view.currentIndex = 5
                }
            }

            Button {
                id: answer_two
                x: 185
                y: 315
                width: 150
                height: 45
                text: qsTr("Собака")
                font.pointSize: 20

                display: AbstractButton.TextOnly
                background: Rectangle {
                    color: "#a8f874"
                }
                //var value = false
                onClicked: {
                    our_answer.text = "Ваш ответ:"+answer_two.text
                    view.currentIndex = 5
                }
            }

            Button {
                id: answer_one
                x: 14
                y: 315
                width: 152
                height: 45
                text: qsTr("Кот")
                focusPolicy: Qt.TabFocus
                display: AbstractButton.TextOnly
                font.pointSize: 20
                background: Rectangle {
                    color: "#a8f874"

                }
                //var value = false
                onClicked: {
                    our_answer.text = "Ваш ответ:"+answer_one.text
                    view.currentIndex = 5
                }
            }

            Text {
                id: element
                x: 52
                y: 46
                color: "#fbfb30"
                text: qsTr("Выберите вариант ответа")
                font.family: "Times New Roman"
                font.pixelSize: 23
            }

            Image {
                id: image
                x: 0
                y: 86
                width: 350
                height: 199
                fillMode: Image.PreserveAspectFit
            }

            Button {
                id: repeat_photo
                x: 15
                y: 451
                width: 321
                height: 40
                text: qsTr("Повторить")
                font.pointSize: 20
                background: Rectangle {
                    color: "#a8f874"
                }
                onClicked: {
                    view.currentIndex = 1
                }
            }

        }

        Page {
             id: sixth
             background: Rectangle{
             color: "green"
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
                        color: "#a8f874"
                    }
                    onClicked: {
                        view.currentIndex = 1
                    }
           }

              Text {
                  id: server_answer
                  x: 52
                  y: 64
                  width: 78
                  height: 14
                  color: "#a8f874"
                  font.pixelSize: 25
              }

              Text {
                  id: our_answer
                  x: 52
                  y: 122
                  color: "#a8f874"
                  font.pixelSize: 25
              }
        }

    }
}










/*##^## Designer {
    D{i:2;anchors_height:600;anchors_width:350;anchors_y:0}
}
 ##^##*/
