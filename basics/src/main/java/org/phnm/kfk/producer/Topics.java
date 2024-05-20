package org.phnm.kfk.producer;

public enum Topics {
    ONE("topic_1"),
    TWO("topic_2");

    private final String topicName;

    Topics(final String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
