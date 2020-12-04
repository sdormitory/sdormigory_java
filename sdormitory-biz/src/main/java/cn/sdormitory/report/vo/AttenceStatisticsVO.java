package cn.sdormitory.report.vo;

/**
 * @创建人：zhouyang
 * @创建时间：2020/12/3 15:54
 * @version：V1.0
 */
public class AttenceStatisticsVO {
    //宿舍ID
    private Long bdormitoryId;
    //宿舍栋号
    private String buildingNo;
    //楼层
    private String storey;
    //宿舍号
    private String dormitoryNo;
    //每个宿舍总人数(根据学生表数据统计)
    private Long totalCount;
    //每个宿舍正常出勤人数
    private Long normalAttenceCount;
    //正常出勤率
    private String normalAttenceRate;
    //人脸识别日期(每天进入时间以最后一次为准)
    private String accessDate;


    public Long getBdormitoryId() {
        return bdormitoryId;
    }

    public void setBdormitoryId(Long bdormitoryId) {
        this.bdormitoryId = bdormitoryId;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getStorey() {
        return storey;
    }

    public void setStorey(String storey) {
        this.storey = storey;
    }

    public String getDormitoryNo() {
        return dormitoryNo;
    }

    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getNormalAttenceCount() {
        return normalAttenceCount;
    }

    public void setNormalAttenceCount(Long normalAttenceCount) {
        this.normalAttenceCount = normalAttenceCount;
    }

    public String getNormalAttenceRate() {
        return normalAttenceRate;
    }

    public void setNormalAttenceRate(String normalAttenceRate) {
        this.normalAttenceRate = normalAttenceRate;
    }

    public String getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }
}
