package models;
public class Lesson {
    private int id;
    private String name;
    private String audience;
    private String teacher;
    private DayOfWeek dayOfWeek;
    private String startTime;
    private String endTime;
    private LessonType lessonType;
    private WeekType weekType;

    private int group;

    public Lesson(int id, String name, String audience, String teacher, DayOfWeek dayOfWeek, String startTime, String endTime, LessonType lessonType, WeekType weekType, int group) {
        this.id = id;
        this.name = name;
        this.audience = audience;
        this.teacher = teacher;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lessonType = lessonType;
        this.weekType = weekType;
        this.group = group;
    }

    public Lesson(String name, String audience, String teacher, DayOfWeek dayOfWeek, String startTime, String endTime, LessonType lessonType, WeekType weekType, int group) {
        this.name = name;
        this.audience = audience;
        this.teacher = teacher;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lessonType = lessonType;
        this.weekType = weekType;
        this.group = group;
    }

    public Lesson() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", audience='" + audience + '\'' +
                ", teacher='" + teacher + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", lessonType=" + lessonType +
                ", weekType=" + weekType +
                ", group=" + group +
                '}';
    }
}