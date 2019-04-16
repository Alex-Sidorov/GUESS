#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QSslSocket>
#include <QtDebug>
#include <file.h>
#include <OpenAndroidGallery.h>
#include <request.h>

int main(int argc, char *argv[])
{
    qmlRegisterType<FileIO, 1>("FileIO", 1, 0, "FileIO");
    qmlRegisterType<Request, 1>("Request", 1, 0, "Request");
    qmlRegisterType<OpenAndroidGallery>("Apadana.OpenAndroidGallery", 1, 0, "OpenAndroidGallery");
    QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);

    QGuiApplication app(argc, argv);
    QQmlApplicationEngine engine;
    engine.load(QUrl(QStringLiteral("qrc:/main.qml")));
    if (engine.rootObjects().isEmpty())
        return -1;

    //qDebug()<<QSslSocket::supportsSsl() << QSslSocket::sslLibraryBuildVersionString() ;
    return app.exec();
}
