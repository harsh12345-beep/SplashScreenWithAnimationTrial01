package com.harsh.kafka


import com.fasterxml.jackson.databind.deser.std.StringDeserializer

import java.time.Duration
import java.util.*


object ProducerAndConsumer {

    private fun Consumer() {
        println("consumer started")
        val bootstrapServers = "127.0.0.1:9092"
        val Group = Math.random().toString()
        val prop = Properties()
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, Group)
        val consumer = KafkaConsumer<String, String>(prop)
        consumer.subscribe(setOf("transaction"))
        while (true) {
            val main = consumer.poll(1000L)
//            val records = consumer.poll(Duration.ofMillis(100))
            for (record in main) {
                println("Consumer " + record.value())
            }
        }
    }

    fun Producer() {
        println("producer started")
        val properties = Properties()
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        val producer = KafkaProducer<String,String>(properties)
        while (true) {
//            val InputByUser = Scanner(System.`in`)
//            val name = InputByUser.nextLine()
            //            ProducerRecord<String, String> record = new ProducerRecord<>("Harsh", "key1", name);
            producer.send(ProducerRecord("transaction", "HELLO I AM FROM ANDROID"))
            producer.flush()

        }
    }

    @JvmStatic
    fun senderAndReciver() {
        val t1 = Thread { Producer() }

        // Create thread t2
        val t2 = Thread { Consumer() }

        // Start both threads
        t1.start()
        t2.start()
    }
}