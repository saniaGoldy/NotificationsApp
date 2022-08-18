# NotificationsApp
Приложение с двумя кнопками

 - start

 - stop

По нажатию на start

 - показывать notification пользователю каждую 1 минуту

 - сохранять в SharedPreferences isShowNotification = true

По нажатию на stop

 - останавливать показ уведомлений

 - сохранять в SharedPreferences isShowNotification = false

Продолжать показывать уведомление пользователю после перезагрузки девайса

 - In bootup receiver

          -check in SharedPreferences

                if isShowNotification

                 - true – start showing notifications

                 - false – do nothing
