import QtQuick.Window 2.1
import QtQuick 2.9
import QtQuick.Controls 2.2
import FileIO 1.0
import Apadana.OpenAndroidGallery 1.0
import Request 1.0

Window {

    id: window
    visible: true
    width: 350
    height: 620
    color: "black"

    title: qsTr("GUESS")

    Request {
        id: my_request
        onRecv_response: {
            console.log(response)


            /*if (request.readyState === XMLHttpRequest.DONE)
            {
                if (request.status === 200 && view.currentIndex === 3)
                {
                    var JsonObject= JSON.parse(request.responseText)
                    var text = request.responseText
                    server_answer.text = "Верный ответ:" + text.substring(3,text.length-23)
                    if(server_answer.text ==="do")
                    {
                        server_answer.text = "Верный ответ:" + text.substring(3,text.length-22)
                    }

                    view.currentIndex = 4
                    console.log(request.responseText)
                    //console.log(text.substring(3,text.length-23))
                }
                else
                {
                    //bad_request.visible = true
                    error_request1.visible = true
                    error_request2.visible = true
                    error_request3.visible = true
                    console.log("HTTP request failed", request.status,request.responseText)
                }
            }*/
        }
    }

    Item {
        id: image_path
        FileIO {
            id: myFile
            //source: image.source
            source: image_path.camera_or_gallerey ? image_path.gallerey_path : image_path.camera_path
            onError: console.log(msg)
        }
        property bool camera_or_gallerey
        property string camera_path
        property string gallerey_path
    }

    OpenAndroidGallery {
        id: openG
        onSigSendPath: {
            //send_image.source = "file://" + path


            /*image.source = "file://" + path
            //myFile.source = "file://" + path
            image_path.camera_path = "file://" + path*/
            image_path.camera_or_gallerey = 1
            view.currentIndex = 2
        }
    }

    Drawer {
        id: drawer
        width: 0.65 * window.width
        height: window.height
        enabled: view.currentIndex != 3
        background: Rectangle {
            color: "white"
        }
        Button {
            id: main_page_button
            x: 0
            y: 50
            width: 228
            height: 44
            text: qsTr("Главная")
            font.pointSize: 25
            anchors.verticalCenterOffset: -273
            anchors.verticalCenter: parent.verticalCenter
            visible: true
            background: Rectangle {
                color: "white"
            }
            onClicked: {
                view.currentIndex = 0
                drawer.close()
                clear_text_field()
            }
        }
        Button {
            id: signin_button
            x: 0
            y: 50
            width: 228
            height: 44
            text: qsTr("Войти")
            font.pointSize: 25
            anchors.verticalCenterOffset: -228
            anchors.verticalCenter: parent.verticalCenter
            visible: true
            background: Rectangle {
                color: "white"
            }
            onClicked: {
                signin_page.visible_error_1 = false
                signin_page.visible_error_2 = false
                view.currentIndex = 6
                drawer.close()
            }
        }
        Button {
            id: info_button
            x: 0
            y: 50
            width: 228
            height: 44
            text: qsTr("Профиль")
            font.pointSize: 25
            anchors.verticalCenterOffset: -228
            anchors.verticalCenter: parent.verticalCenter
            visible: !signin_button.visible
            background: Rectangle {
                color: "white"
            }
            onClicked: {
                profile_page.load = true
                profile_page.visible_info = false

                drawer.close()
                view.currentIndex = 9
                var request = new XMLHttpRequest
                request.open('GET',
                             'http://guess-dev.herokuapp.com/api/v1/profile')
                request.setRequestHeader('Authorization', access.access_token)
                request.onreadystatechange = function () {
                    if (request.readyState === XMLHttpRequest.DONE) {
                        if (request.status === 200) {
                            var JsonObject = JSON.parse(request.responseText)
                            var json_obj = JSON.parse(request.responseText)
                            profile.id_user = json_obj.id
                            profile.first_name = json_obj.firstName
                            profile.last_name = json_obj.lastName
                            profile.email = json_obj.email
                            profile_page.visible_info = true
                        } else {
                            console.log("HTTP request failed",
                                        request.status /*,request.responseText*/)
                        }
                        profile_page.load = false
                    }
                }
                request.send()
            }
        }
        Button {
            id: change_name_button
            x: 0
            y: 50
            width: 228
            height: 44
            text: qsTr("Изменить имя")
            font.pointSize: 25
            anchors.verticalCenterOffset: -188
            anchors.verticalCenter: parent.verticalCenter
            visible: !signup_button.visible
            background: Rectangle {
                color: "white"
            }
            onClicked: {
                change_name_page.visible_complete = false
                change_name_page.visible_error_1 = false
                change_name_page.visible_error_2 = false
                drawer.close()
                view.currentIndex = 8
            }
        }

        Button {
            id: change_password_button
            x: 0
            y: 50
            width: 228
            height: 44
            text: qsTr("Изменить пароль")
            font.pointSize: 25
            anchors.verticalCenterOffset: -148
            anchors.verticalCenter: parent.verticalCenter
            visible: !signup_button.visible
            background: Rectangle {
                color: "white"
            }
            onClicked: {
                change_password_page.visible_error_1 = false
                change_password_page.visible_error_2 = false
                change_password_page.visible_complete = false
                drawer.close()
                view.currentIndex = 10
            }
        }

        Button {
            id: signup_button
            x: 0
            y: 50
            width: 228
            height: 44
            text: qsTr("Регистрация")
            font.pointSize: 25
            anchors.verticalCenterOffset: -188
            visible: true
            anchors.verticalCenter: parent.verticalCenter
            background: Rectangle {
                color: "white"
            }
            onClicked: {
                signup_page.visible_error_1 = false
                signup_page.visible_error_2 = false
                view.currentIndex = 7
                drawer.close()
            }
        }
        Button {
            id: exit_button
            x: 0
            y: 50
            width: 228
            height: 44
            font.pointSize: 25
            anchors.verticalCenterOffset: 278
            anchors.verticalCenter: parent.verticalCenter
            visible: false
            text: qsTr("Выход")
            background: Rectangle {
                color: "white"
            }
            onClicked: {
                view.currentIndex = 0
                exit_button.visible = false
                signin_button.visible = true
                signup_button.visible = true
                drawer.close()
            }
        }
    }

    SwipeView {
        id: view
        y: 0
        width: 350
        height: 620
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

        focus: true
        Keys.enabled: false

        MainPage {
            id: main_page
            onClicked_start: view.currentIndex = 1
        }

        CameraPage {
            id: camera_page
            height_video_output: window.height
            width_video_output: window.width
            onImage_capture: {
                view.currentIndex = 2
                send_image_page.source_image = preview
                quiz_page.image_source = preview
                image_path.camera_path = preview
                image_path.camera_or_gallerey = 0
            }
        }

        SendImagePage {
            id: send_image_page
            onClicked_send: send_image()
            onClicked_repeat: view.currentIndex = 1
        }

        WaitPage {
            id: wait_page
            onCancel_clicked: view.currentIndex = 2
        }

        QuizPage {
            id: quiz_page
            height_image: window.height
            width_image: window.width
            onClicked_answer: {
                console.log(answer)
                view.currentIndex = 5
            }
            onClicked_repeat: view.currentIndex = 1
        }

        ResultPage {
            id: result_page
            onClicked_repeat: {
                view.currentIndex = 1
            }
        }

        SigninPage {
            id: signin_page
            onClicked_input: {
                if (is_empty) {
                    signin_page.visible_error_1 = true
                    signin_page.visible_error_2 = false
                    signin_page.text_error_1 = "Заполните все поля"
                    return
                }
                signin_page.load = true
                signin_page.enabled = false
                signin()
            }
        }

        SignupPage {
            id: signup_page
            width_fields: window.width
            onClicked_signup: {
                signup()
            }
        }

        ChangeNamePage {
            id: change_name_page
            width_fields: window.width
            onClicked_change: {
                change_name()
            }
        }

        ProfilePage {
            id: profile_page
        }

        ChangePasswordPage {
            id: change_password_page
            width_fields: window.width
            onClicked_change: {
                change_password()
            }
        }
    }

    Item {
        id: jsons
        property var signin_json: {
            "email": signin_page.login,
            "password": signin_page.password
        }
        property var signup_json: {
            "firstName": signup_page.first_name,
            "lastName": signup_page.last_name,
            "email": signup_page.login,
            "password": signup_page.password
        }

        property var change_name_json: {
            "firstName": change_name_page.first_name,
            "lastName": change_name_page.last_name
        }
        property var change_password_json: {
            "currentPassword": change_password_page.old_password,
            "newPassword": change_password_page.new_password
        }
    }

    Item {
        id: profile
        property var id_user
        property var first_name
        property var last_name
        property var email
    }

    Item {
        id: access
        property var access_token
        property var access_issued_at
        property var access_expires_in
        property var refresh_token
        property var refresh_issued_at
        property var refresh_expires_in
    }

    function clear_text_field() {
        change_name_page.clear_field()
        change_password_page.clear_field()
        signin_page.clear_field()
        signup_page.clear_field()
    }

    function get_translate_animal(animal) {
        switch (animal) {
        case "dog":
            return "Собака"
        case "cat":
            return "Кот"
        case "parrot":
            return "Попугай"
        case "goldfish":
            return "Рыбка"
        case "lion":
            return "Лев"
        case "snake":
            return "Змея"
        case "elephant":
            return "Слон"
        case "crocodile":
            return "Крокодил"
        }
    }

    function pars_json(json) {
        var data = new String
        json.forEach(function (x, i, json) {
            data = data + x['name'] + ' '
        })
        return data
    }

    function send_image() {
        wait_page.visible_error = false
        view.currentIndex = 3
        var request = new XMLHttpRequest
        request.open('POST',
                     'http://hidden-fortress-30624.herokuapp.com/api/v1/recognition')
        request.setRequestHeader('Content-Type', 'image/jpg')
        request.onreadystatechange = function () {
            if (request.readyState === XMLHttpRequest.DONE) {
                if (request.status === 200 && view.currentIndex === 3) {
                    var JsonObject = JSON.parse(request.responseText)
                    view.currentIndex = 4
                    var answers = myFile.answer_for_victorine(
                                pars_json(JsonObject)).split(' ')
                    quiz_page.text_answer_one = get_translate_animal(answers[0])
                    quiz_page.text_answer_two = get_translate_animal(answers[1])
                    quiz_page.text_answer_three = get_translate_animal(
                                answers[2])
                    quiz_page.text_answer_four = get_translate_animal(
                                answers[3])

                    result_page.answer = "Животное не найдено"
                    JsonObject.forEach(function (x, i, JsonObject) {
                        console.log(x['name'])
                        result_page.answer = "Ответ:" + get_translate_animal(
                                    x['name'])
                        return
                    })
                    console.log(request.responseText)
                } else {
                    wait_page.visible_error = true
                    console.log("HTTP request failed", request.status,
                                request.responseText)
                }
            }
        }

        var data
        var length
        if (image_path.camera_or_gallerey) {
            data = openG.get_file()
            console.log(data)
            length = openG.get_size()
            console.log(length)
        } else {
            data = myFile.read()
            console.log(data)
            length = myFile.size_data()
            console.log(length)
        }
        request.setRequestHeader('Content-Length', length)
        request.send(data)
    }

    function signin() {
        var request = new XMLHttpRequest
        request.open('POST',
                     'http://guess-dev.herokuapp.com/api/v1/auth/signin')
        request.setRequestHeader('Content-Type', 'application/json')
        var data = JSON.stringify(jsons.signin_json)
        request.setRequestHeader('Content-Length', data.length)
        request.send(data)
        request.onreadystatechange = function () {
            if (request.readyState === XMLHttpRequest.DONE) {
                if (request.status === 201) {
                    var json_obj = JSON.parse(request.responseText)
                    access.access_token = json_obj.accessToken
                    access.access_issued_at = json_obj.accessIssuedAt
                    access.access_expires_in = json_obj.accessExpiresIn
                    access.refresh_token = json_obj.refreshToken
                    access.refresh_issued_at = json_obj.refreshIssuedAt
                    access.refresh_expires_in = json_obj.refreshExpiresIn
                    console.log(request.responseText)
                    signin_button.visible = false
                    signup_button.visible = false
                    exit_button.visible = true
                    view.currentIndex = 0
                    clear_text_field()
                } else {
                    signin_page.visible_error_1 = true
                    signin_page.visible_error_2 = true
                    console.log(request.responseText)
                    if (request.status === 0
                            || request.responseText.length === 0) {
                        signin_page.text_error_1 = "Проверьте соединение"
                        signin_page.text_error_2 = "с интернетом"
                    }
                    signin_page.text_error_1 = "Пользователь не найден"
                    signin_page.visible_error_2 = false
                }
                signin_page.enabled = true
                signin_page.load = false
            }
        }
    }

    function signup() {
        var request = new XMLHttpRequest
        request.open('POST',
                     'http://guess-dev.herokuapp.com/api/v1/auth/signup')
        request.setRequestHeader('Content-Type', 'application/json')
        var data = JSON.stringify(jsons.signup_json)
        request.setRequestHeader('Content-Length', data.length)
        request.send(data)
        signup_page.load = true
        signup_page.enabled = false
        request.onreadystatechange = function () {
            if (request.readyState === XMLHttpRequest.DONE) {
                if (request.status === 201) {
                    var json_obj = JSON.parse(request.responseText)
                    profile.id_user = json_obj.id
                    profile.first_name = json_obj.firstName
                    profile.last_name = json_obj.lastName
                    profile.email = json_obj.email
                    console.log(request.responseText)
                    signup_page.visible_error_1 = false
                    signup_page.visible_error_1 = false
                    signin_page.visible_error_1 = false
                    signin_page.visible_error_2 = false
                    view.currentIndex = 6
                    clear_text_field()
                } else {
                    signup_page.visible_error_1 = true
                    signup_page.visible_error_2 = true
                    console.log(request.responseText)
                    if (request.status === 0
                            || request.responseText.length === 0) {
                        signup_page.text_error_1 = "Проверьте соединение"
                        signup_page.text_error_2 = "с интернетом"
                    }
                    signup_page.text_error_1 = "Такой логин уже занят"
                    signup_page.visible_error_2 = false
                }
                signup_page.enabled = true
                signup_page.load = false
            }
        }
    }

    function change_name() {
        var request = new XMLHttpRequest
        request.open('PUT', 'http://guess-dev.herokuapp.com/api/v1/profile')
        request.setRequestHeader('Content-Type', 'application/json')
        request.setRequestHeader('Authorization', access.access_token)
        var data = JSON.stringify(jsons.change_name_json)
        request.setRequestHeader('Content-Length', data.length)
        request.send(data)
        change_name_page.load = true
        change_name_page.enabled = false
        request.onreadystatechange = function () {
            if (request.readyState === XMLHttpRequest.DONE) {
                if (request.status === 200) {
                    var json_obj = JSON.parse(request.responseText)
                    profile.id_user = json_obj.id
                    profile.first_name = json_obj.firstName
                    profile.last_name = json_obj.lastName
                    profile.email = json_obj.email
                    change_name_page.visible_error_1 = false
                    change_name_page.visible_error_2 = false
                    change_name_page.visible_complete = true
                    console.log(request.responseText)
                    clear_text_field()
                } else {
                    console.log(request.responseText)
                    change_name_page.visible_error_1 = true
                    change_name_page.visible_error_2 = true
                    change_name_page.visible_complete = false
                    if (request.status === 0
                            || request.responseText.length === 0) {
                        change_name_page.text_error_1 = "Проверьте соединение"
                        change_name_page.text_error_2 = "с интернетом"
                    }
                }
                change_name_page.enabled = true
                change_name_page.load = false
            }
        }
    }

    function change_password() {
        var request = new XMLHttpRequest
        request.open('PUT',
                     'http://guess-dev.herokuapp.com/api/v1/profile/change-password')
        request.setRequestHeader('Content-Type', 'application/json')
        request.setRequestHeader('Authorization', access.access_token)
        var data = JSON.stringify(jsons.change_password_json)
        request.setRequestHeader('Content-Length', data.length)
        request.send(data)
        change_password_page.load = true
        change_password_page.enabled = false
        request.onreadystatechange = function () {
            if (request.readyState === XMLHttpRequest.DONE) {
                if (request.status === 200) {
                    change_password_page.visible_error_1 = false
                    change_password_page.visible_error_2 = false
                    change_password_page.visible_complete = true
                    change_password_page.enabled = true
                    console.log(request.responseText)
                    clear_text_field()
                } else {
                    console.log(request.responseText)
                    change_password_page.enabled = true
                    change_password_page.visible_error_1 = true
                    change_password_page.visible_error_2 = true
                    if (request.status === 0) {
                        change_password_page.text_error_1 = "Проверьте соединение"
                        change_password_page.text_error_2 = "с интернетом"
                    } else {
                        change_password_page.text_error_1 = "Пароль не верен"
                        change_password_page.visible_error_2 = false
                        change_password_page.visible_complete = false
                    }
                }
                change_password_page.enabled = true
                change_password_page.load = false
            }
        }
    }
}
