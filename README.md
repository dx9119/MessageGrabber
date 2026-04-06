# MessageGrabber
Сервис для парсинга сообщений из чатов стримов Twitch и live.vkvideo с последующей публикацией в Kafka или RabbitMQ.
# Настройки
В application.properties указывается профиль в зависимости от выбранного брокера, а также локальный профиль.
Профиль local должен содержать данные для доступа к API VK и Twitch.
В application.properties также задаётся источник сообщений. Сейчас поддерживается только парсинг чата Twitch. VK, вероятно, не будет доступен, хотя подготовлена базовая интеграция через centrifuge-java.
# Очередь перед очередью?
Сообщения из всех источников помещаются в ArrayBlockingQueue (BufferService).
Этот буфер создан для того, чтобы можно было гибко включать и отключать разные источники сообщений.

BufferConsumer — асинхронный потребитель буфера. Он непрерывно извлекает сообщения из BufferService и отправляет их в брокер через контракт IMessageBrokerPublisher.
Публикация выполняется с помощью цепочки самовоспроизводимых задач в ThreadPoolExecutor.
# application-local.properties
```
tw.twitch-token=
tw.twitch-channel=
vk.ws-url=wss://pubsub-dev.live.vkvideo.ru/connection/websocket?format=json&cf_protocol_version=v2
vk.ws-token=
vk.ws-sub-token=
vk.chat-id=
```