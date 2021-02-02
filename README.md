# HatGame представляет собой игру, похожую на "Выкрутасы". В финальной версии планируется следующий функционал:
1. Выбор количества команд
2. Выбор режима игры: новая или сохраненная
3. В режиме новой игры будет возможность выбирать темы (категории) слов, которые будут формироваться случайно
3. Возможность добавлять свои слова, если игра новая
4. Возможность направлять ссылку на игру через мессенджеры, чтобы другие участники могли посмотреть ход игры: - какие слова были угаданы, какой счет и т.п.

На данный момент структура приложения следующая:
- предполагается использование одного активити с множеством фрагментов
- фрагмент MainActivity отвечает за стартовое окно с выбором режима игры
- фрагмент GameActivity отвечает за протекание игры. По сути в нем, есть текстовое поле со словом и две кнопки (угадал/не угадал)
- фракгмент ResultFragmetn отвечает за подведение итогов. Сколько слов угадано из общего количества слов
- организовано хранение слов на Cloud Firestore
