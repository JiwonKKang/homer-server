package com.dbs.homer.repository.domain;

public enum BaseballPosition {
    BULLPEN_PITCHER(0, "중간 계투 (불펜 투수)"),
    STARTING_PITCHER(1, "선발 투수"),
    CATCHER(2, "포수"),
    FIRST_BASE(3, "1루수"),
    SECOND_BASE(4, "2루수"),
    THIRD_BASE(5, "3루수"),
    SHORTSTOP(6, "유격수"),
    LEFT_FIELD(7, "좌익수"),
    CENTER_FIELD(8, "중견수"),
    RIGHT_FIELD(9, "우익수"),
    DESIGNATED_HITTER(10, "지명타자");

    private final int code;
    private final String description;

    BaseballPosition(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 예를 들어, 코드로부터 Enum 상수를 얻기 위한 정적 메서드
    public static BaseballPosition getByCode(int code) {
        for (BaseballPosition position : BaseballPosition.values()) {
            if (position.code == code) {
                return position;
            }
        }
        throw new IllegalArgumentException("Invalid BaseballPosition code: " + code);
    }
}
