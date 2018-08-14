package com.xxm.it.system.vo;

public class LeaseInfoVO extends CommonVO {

 private static final long serialVersionUID = 1L;
// private String leaseInfoId; //租赁信息ID
// private String baseLeaseInfoId; //基础租赁信息ID
// private String carTypeVL; // 车产类型，主车/挂车
// private String carTypeName; //车产类型名称
// private String manufacturer; //制造商
// private String brand; //品牌
// private String carName; //车名称
// private String model; //型号
// private String leaseholdUse; //租赁使用用途
// private String leaseholdArea; //租赁物使用区域
   private String engineNumber; //发动机号
   private String frameNumber; //车架号
// private String drivingForm; //驱动形式
// private String engineModel; //发动机型号
// private String enginePower; //发动机功率
// private String carColor; // 车颜色
   private String plateNumber; //车牌号
// private String otherInstructions; //其他配置说明
// private String applyInfoId; //
// private String status; //状态  0为可查  1为删除
// private String[] leaseInfoIds = new String[] {}; //批量删除时存放id值
// 
// private String installId;//安装表外键
// private String runAreaName;//车险保单号
// private String startSiteName;//续保车险保单号一
// private String licenseTypeProductConfig;//种类
// private String licenseColorCodeProductConfig;//底色
// private String carKindCode;//车辆种类
// private String carKindCodeName;//车辆种类名称
// private String engineNo;//发动机
// private String vinNo;//VIN
// private String enrollDate;//初登日期
// private String brandName;//车型代码
// private String seatCount;//载客
// private String exhaustScale;//排量
// private String tonCount;//载质量 单位：吨
// private String purchasePrice;//车辆净价
// private String invoiceNo;//购车发票号
// private String loanNo1;//购车合同号
// private String planAmount;//续保保证金
// private String unitAmount;//抵押保证金
// private String loanWay;//客户来源
// private String loanWayName;//客户来源名称
// private String carKind;//车辆状态
// private String carType;//所购/所租车辆类型
// private String carTypeBaseName;//所购/所租车辆类型名称
// private String mainBd;//主品牌
// private String carlne;//车系
// private String carTp;//车款
// private String regsNo;//原车牌号
// private String gtreDt;//抵押登记日期
// private String sealAm;//销售指导价
// private String tripartiteEv;//第三方评估价
// private String rbEvaluate;//人保评估价
// private String drivingMileage;//行驶里程数 公里
// private String tranAm;//实际成交价
// private String carAge;//车龄
// private String powerctetype;//动力系统类别
// private String buycarcustname;//购车人
// private String chnelbusno;//渠道商唯一标识
// private String chnelbusname;//渠道商名称
// private String chnelbusadr;//渠道商所在地
// private String sellername;//售车方名称
// private String selleraddress;//售车方所在地
// private String payeename;//收款方名称
// private String isotherbank;//是否他行
// private String isotherbankName;//是否他行名称
// private String payeetype;//收款方账号类型
// private String payeetypeName;//收款方账号类型名称
// private String payeeacct;//收款方账号
// private String payeebank;//收款方开户行
// private String payeebankno;//收款方开户行行号
// private String carowner;//车辆所有人
// private String leasecmpname;//租赁公司名称
// private String leasecmpadr;//租赁公司地址
// private String imeino;//IMEI编号
// private String gpsstt;//GPS状态
// private String gpssttName;//GPS状态名称
// private String storena;//4s店名称
// private String ifwhiteflag;//是否为‘白户’
// private String ifwhiteflagName;//是否为‘白户’名称
// private String useNature;//原车辆使用性质
// private String useNatureName;//原车辆使用性质名称
// private String invoiceValue;//发票金额
// private String stepHull;//所购车辆种类
// private String sealsn;//SN号
// private String gpsput;//GPS厂家
// private String usesTp;//车辆用途
// 
// 
//public String getStepHull() {
//	return stepHull;
//}
//public void setStepHull(String stepHull) {
//	this.stepHull = stepHull;
//}
//public String getSealsn() {
//	return sealsn;
//}
//public void setSealsn(String sealsn) {
//	this.sealsn = sealsn;
//}
//public String getGpsput() {
//	return gpsput;
//}
//public void setGpsput(String gpsput) {
//	this.gpsput = gpsput;
//}
//public String getUsesTp() {
//	return usesTp;
//}
//public void setUsesTp(String usesTp) {
//	this.usesTp = usesTp;
//}
//public String getLeaseInfoId() {
//	return leaseInfoId;
//}
//public void setLeaseInfoId(String leaseInfoId) {
//	this.leaseInfoId = leaseInfoId;
//}
//public String getBaseLeaseInfoId() {
//	return baseLeaseInfoId;
//}
//public void setBaseLeaseInfoId(String baseLeaseInfoId) {
//	this.baseLeaseInfoId = baseLeaseInfoId;
//}
//public String getCarTypeVL() {
//	return carTypeVL;
//}
//public void setCarTypeVL(String carTypeVL) {
//	this.carTypeVL = carTypeVL;
//}
//public String getManufacturer() {
//	return manufacturer;
//}
//public void setManufacturer(String manufacturer) {
//	this.manufacturer = manufacturer;
//}
//public String getBrand() {
//	return brand;
//}
//public void setBrand(String brand) {
//	this.brand = brand;
//}
//public String getCarName() {
//	return carName;
//}
//public void setCarName(String carName) {
//	this.carName = carName;
//}
//public String getModel() {
//	return model;
//}
//public void setModel(String model) {
//	this.model = model;
//}
//public String getLeaseholdUse() {
//	return leaseholdUse;
//}
//public void setLeaseholdUse(String leaseholdUse) {
//	this.leaseholdUse = leaseholdUse;
//}
//public String getLeaseholdArea() {
//	return leaseholdArea;
//}
//public void setLeaseholdArea(String leaseholdArea) {
//	this.leaseholdArea = leaseholdArea;
//}
  public String getEngineNumber() {
	return engineNumber;
  }
  public void setEngineNumber(String engineNumber) {
	this.engineNumber = engineNumber;
  }
  public String getFrameNumber() {
	return frameNumber;
  }
  public void setFrameNumber(String frameNumber) {
	this.frameNumber = frameNumber;
  }
//public String getDrivingForm() {
//	return drivingForm;
//}
//public void setDrivingForm(String drivingForm) {
//	this.drivingForm = drivingForm;
//}
//public String getEngineModel() {
//	return engineModel;
//}
//public void setEngineModel(String engineModel) {
//	this.engineModel = engineModel;
//}
//public String getEnginePower() {
//	return enginePower;
//}
//public void setEnginePower(String enginePower) {
//	this.enginePower = enginePower;
//}
//public String getCarColor() {
//	return carColor;
//}
//public void setCarColor(String carColor) {
//	this.carColor = carColor;
//}
   public String getPlateNumber() {
	   return plateNumber;
   }
   public void setPlateNumber(String plateNumber) {
	   this.plateNumber = plateNumber;
   }
//public String getOtherInstructions() {
//	return otherInstructions;
//}
//public void setOtherInstructions(String otherInstructions) {
//	this.otherInstructions = otherInstructions;
//}
//public String getApplyInfoId() {
//	return applyInfoId;
//}
//public void setApplyInfoId(String applyInfoId) {
//	this.applyInfoId = applyInfoId;
//}
//public String getStatus() {
//	return status;
//}
//public void setStatus(String status) {
//	this.status = status;
//}
//public String getCarTypeName() {
//	return carTypeName;
//}
//public void setCarTypeName(String carTypeName) {
//	this.carTypeName = carTypeName;
//}
//public String[] getLeaseInfoIds() {
//	return leaseInfoIds;
//}
//public void setLeaseInfoIds(String[] leaseInfoIds) {
//	this.leaseInfoIds = leaseInfoIds;
//}
//public String getInstallId() {
//	return installId;
//}
//public void setInstallId(String installId) {
//	this.installId = installId;
//}
//public String getRunAreaName() {
//	return runAreaName;
//}
//public void setRunAreaName(String runAreaName) {
//	this.runAreaName = runAreaName;
//}
//public String getStartSiteName() {
//	return startSiteName;
//}
//public void setStartSiteName(String startSiteName) {
//	this.startSiteName = startSiteName;
//}
//public String getLicenseTypeProductConfig() {
//	return licenseTypeProductConfig;
//}
//public void setLicenseTypeProductConfig(String licenseTypeProductConfig) {
//	this.licenseTypeProductConfig = licenseTypeProductConfig;
//}
//public String getLicenseColorCodeProductConfig() {
//	return licenseColorCodeProductConfig;
//}
//public void setLicenseColorCodeProductConfig(String licenseColorCodeProductConfig) {
//	this.licenseColorCodeProductConfig = licenseColorCodeProductConfig;
//}
//public String getCarKindCode() {
//	return carKindCode;
//}
//public void setCarKindCode(String carKindCode) {
//	this.carKindCode = carKindCode;
//}
//public String getEngineNo() {
//	return engineNo;
//}
//public void setEngineNo(String engineNo) {
//	this.engineNo = engineNo;
//}
//public String getVinNo() {
//	return vinNo;
//}
//public void setVinNo(String vinNo) {
//	this.vinNo = vinNo;
//}
//public String getEnrollDate() {
//	return enrollDate;
//}
//public void setEnrollDate(String enrollDate) {
//	this.enrollDate = enrollDate;
//}
//public String getBrandName() {
//	return brandName;
//}
//public void setBrandName(String brandName) {
//	this.brandName = brandName;
//}
//public String getSeatCount() {
//	return seatCount;
//}
//public void setSeatCount(String seatCount) {
//	this.seatCount = seatCount;
//}
//public String getExhaustScale() {
//	return exhaustScale;
//}
//public void setExhaustScale(String exhaustScale) {
//	this.exhaustScale = exhaustScale;
//}
//public String getTonCount() {
//	return tonCount;
//}
//public void setTonCount(String tonCount) {
//	this.tonCount = tonCount;
//}
//public String getPurchasePrice() {
//	return purchasePrice;
//}
//public void setPurchasePrice(String purchasePrice) {
//	this.purchasePrice = purchasePrice;
//}
//public String getInvoiceNo() {
//	return invoiceNo;
//}
//public void setInvoiceNo(String invoiceNo) {
//	this.invoiceNo = invoiceNo;
//}
//public String getLoanNo1() {
//	return loanNo1;
//}
//public void setLoanNo1(String loanNo1) {
//	this.loanNo1 = loanNo1;
//}
//public String getPlanAmount() {
//	return planAmount;
//}
//public void setPlanAmount(String planAmount) {
//	this.planAmount = planAmount;
//}
//public String getUnitAmount() {
//	return unitAmount;
//}
//public void setUnitAmount(String unitAmount) {
//	this.unitAmount = unitAmount;
//}
//public String getLoanWay() {
//	return loanWay;
//}
//public void setLoanWay(String loanWay) {
//	this.loanWay = loanWay;
//}
//public String getCarKind() {
//	return carKind;
//}
//public void setCarKind(String carKind) {
//	this.carKind = carKind;
//}
//public String getCarType() {
//	return carType;
//}
//public void setCarType(String carType) {
//	this.carType = carType;
//}
//public String getMainBd() {
//	return mainBd;
//}
//public void setMainBd(String mainBd) {
//	this.mainBd = mainBd;
//}
//public String getCarlne() {
//	return carlne;
//}
//public void setCarlne(String carlne) {
//	this.carlne = carlne;
//}
//public String getCarTp() {
//	return carTp;
//}
//public void setCarTp(String carTp) {
//	this.carTp = carTp;
//}
//public String getRegsNo() {
//	return regsNo;
//}
//public void setRegsNo(String regsNo) {
//	this.regsNo = regsNo;
//}
//public String getGtreDt() {
//	return gtreDt;
//}
//public void setGtreDt(String gtreDt) {
//	this.gtreDt = gtreDt;
//}
//public String getSealAm() {
//	return sealAm;
//}
//public void setSealAm(String sealAm) {
//	this.sealAm = sealAm;
//}
//public String getTripartiteEv() {
//	return tripartiteEv;
//}
//public void setTripartiteEv(String tripartiteEv) {
//	this.tripartiteEv = tripartiteEv;
//}
//public String getRbEvaluate() {
//	return rbEvaluate;
//}
//public void setRbEvaluate(String rbEvaluate) {
//	this.rbEvaluate = rbEvaluate;
//}
//public String getDrivingMileage() {
//	return drivingMileage;
//}
//public void setDrivingMileage(String drivingMileage) {
//	this.drivingMileage = drivingMileage;
//}
//public String getTranAm() {
//	return tranAm;
//}
//public void setTranAm(String tranAm) {
//	this.tranAm = tranAm;
//}
//public String getCarAge() {
//	return carAge;
//}
//public void setCarAge(String carAge) {
//	this.carAge = carAge;
//}
//public String getPowerctetype() {
//	return powerctetype;
//}
//public void setPowerctetype(String powerctetype) {
//	this.powerctetype = powerctetype;
//}
//public String getBuycarcustname() {
//	return buycarcustname;
//}
//public void setBuycarcustname(String buycarcustname) {
//	this.buycarcustname = buycarcustname;
//}
//public String getChnelbusno() {
//	return chnelbusno;
//}
//public void setChnelbusno(String chnelbusno) {
//	this.chnelbusno = chnelbusno;
//}
//public String getChnelbusname() {
//	return chnelbusname;
//}
//public void setChnelbusname(String chnelbusname) {
//	this.chnelbusname = chnelbusname;
//}
//public String getChnelbusadr() {
//	return chnelbusadr;
//}
//public void setChnelbusadr(String chnelbusadr) {
//	this.chnelbusadr = chnelbusadr;
//}
//public String getSellername() {
//	return sellername;
//}
//public void setSellername(String sellername) {
//	this.sellername = sellername;
//}
//public String getSelleraddress() {
//	return selleraddress;
//}
//public void setSelleraddress(String selleraddress) {
//	this.selleraddress = selleraddress;
//}
//public String getPayeename() {
//	return payeename;
//}
//public void setPayeename(String payeename) {
//	this.payeename = payeename;
//}
//public String getIsotherbank() {
//	return isotherbank;
//}
//public void setIsotherbank(String isotherbank) {
//	this.isotherbank = isotherbank;
//}
//public String getPayeetype() {
//	return payeetype;
//}
//public void setPayeetype(String payeetype) {
//	this.payeetype = payeetype;
//}
//public String getPayeeacct() {
//	return payeeacct;
//}
//public void setPayeeacct(String payeeacct) {
//	this.payeeacct = payeeacct;
//}
//public String getPayeebank() {
//	return payeebank;
//}
//public void setPayeebank(String payeebank) {
//	this.payeebank = payeebank;
//}
//public String getPayeebankno() {
//	return payeebankno;
//}
//public void setPayeebankno(String payeebankno) {
//	this.payeebankno = payeebankno;
//}
//public String getCarowner() {
//	return carowner;
//}
//public void setCarowner(String carowner) {
//	this.carowner = carowner;
//}
//public String getLeasecmpname() {
//	return leasecmpname;
//}
//public void setLeasecmpname(String leasecmpname) {
//	this.leasecmpname = leasecmpname;
//}
//public String getLeasecmpadr() {
//	return leasecmpadr;
//}
//public void setLeasecmpadr(String leasecmpadr) {
//	this.leasecmpadr = leasecmpadr;
//}
//public String getImeino() {
//	return imeino;
//}
//public void setImeino(String imeino) {
//	this.imeino = imeino;
//}
//public String getGpsstt() {
//	return gpsstt;
//}
//public void setGpsstt(String gpsstt) {
//	this.gpsstt = gpsstt;
//}
//public String getStorena() {
//	return storena;
//}
//public void setStorena(String storena) {
//	this.storena = storena;
//}
//public String getIfwhiteflag() {
//	return ifwhiteflag;
//}
//public void setIfwhiteflag(String ifwhiteflag) {
//	this.ifwhiteflag = ifwhiteflag;
//}
//public String getUseNature() {
//	return useNature;
//}
//public void setUseNature(String useNature) {
//	this.useNature = useNature;
//}
//public String getInvoiceValue() {
//	return invoiceValue;
//}
//public void setInvoiceValue(String invoiceValue) {
//	this.invoiceValue = invoiceValue;
//}
//public static long getSerialversionuid() {
//	return serialVersionUID;
//}
//public String getCarKindCodeName() {
//	return carKindCodeName;
//}
//public void setCarKindCodeName(String carKindCodeName) {
//	this.carKindCodeName = carKindCodeName;
//}
//public String getLoanWayName() {
//	return loanWayName;
//}
//public void setLoanWayName(String loanWayName) {
//	this.loanWayName = loanWayName;
//}
//public String getCarTypeBaseName() {
//	return carTypeBaseName;
//}
//public void setCarTypeBaseName(String carTypeBaseName) {
//	this.carTypeBaseName = carTypeBaseName;
//}
//public String getIsotherbankName() {
//	return isotherbankName;
//}
//public void setIsotherbankName(String isotherbankName) {
//	this.isotherbankName = isotherbankName;
//}
//public String getPayeetypeName() {
//	return payeetypeName;
//}
//public void setPayeetypeName(String payeetypeName) {
//	this.payeetypeName = payeetypeName;
//}
//public String getGpssttName() {
//	return gpssttName;
//}
//public void setGpssttName(String gpssttName) {
//	this.gpssttName = gpssttName;
//}
//public String getIfwhiteflagName() {
//	return ifwhiteflagName;
//}
//public void setIfwhiteflagName(String ifwhiteflagName) {
//	this.ifwhiteflagName = ifwhiteflagName;
//}
//public String getUseNatureName() {
//	return useNatureName;
//}
//public void setUseNatureName(String useNatureName) {
//	this.useNatureName = useNatureName;
//}
 
 
 
}
