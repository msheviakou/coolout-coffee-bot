package by.coolout.bot.statics;

public enum Volumes {

    VOLUME_20 (20),
    VOLUME_42 (42),
    VOLUME_120 (120),
    VOLUME_180 (180),
    VOLUME_250 (250),
    VOLUME_300 (300),
    VOLUME_350 (350);

    private Integer volume;

    Volumes(Integer volume) {
        this.volume = volume;
    }

    public Integer getVolume() {
        return volume;
    }
}
