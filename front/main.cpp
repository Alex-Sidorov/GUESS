#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QSslSocket>
#include <QtDebug>
#include <file.h>

int main(int argc, char *argv[])
{
    qmlRegisterType<FileIO, 1>("FileIO", 1, 0, "FileIO");
    QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);

    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;
    engine.load(QUrl(QStringLiteral("qrc:/main.qml")));
    if (engine.rootObjects().isEmpty())
        return -1;

    qDebug()<<QSslSocket::supportsSsl() << QSslSocket::sslLibraryBuildVersionString() ;
    return app.exec();
}
