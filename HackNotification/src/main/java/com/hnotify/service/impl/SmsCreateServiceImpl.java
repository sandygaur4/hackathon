package com.hnotify.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnotify.dao.domain.SysEmailSmsTemplateVO;
import com.hnotify.dao.domain.SysSMSVO;
import com.hnotify.service.SmsCreateService;

@Service
@Transactional
public class SmsCreateServiceImpl implements SmsCreateService {

	Logger slf4jLogger = LoggerFactory.getLogger("ws");
/*
	@Override
	public SysSMSVO getForgotSms(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysActualUserVO sysActualUserVO, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setMobilenum(sysActualUserVO.getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));
		sysSMSVO.setTimestamp(new java.util.Date());

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		String body = SMS_BODY.replace("<OTP>", sysActualUserVO.getOpt());

		sysSMSVO.setBookingid(String.valueOf(sysActualUserVO.getPkProfilerId()));
		sysSMSVO.setType("FORGOTPASSWORD");

		sysSMSVO.setSmsbody(body);

		return sysSMSVO;

	}

	@Override
	public SysSMSVO getFBDeviceNfSMS(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysActualUserVO sysActualUserVO, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setMobilenum(sysActualUserVO.getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));
		sysSMSVO.setTimestamp(new java.util.Date());

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		String body = SMS_BODY.replace("<OTP>", sysActualUserVO.getOpt());

		sysSMSVO.setSmsbody(body);

		sysSMSVO.setBookingid(String.valueOf(sysActualUserVO.getPkProfilerId()));
		sysSMSVO.setType("FB_DEVICE_NF_OTP");

		return sysSMSVO;
	}

	@Override
	public SysSMSVO getEditProfile(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysActualUserVO sysActualUserVO, String mobile,
			String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setMobilenum(mobile);
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));
		sysSMSVO.setTimestamp(new java.util.Date());

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		String body = SMS_BODY.replace("<OTP>", sysActualUserVO.getOpt());

		sysSMSVO.setBookingid(String.valueOf(sysActualUserVO.getPkProfilerId()));
		sysSMSVO.setType("EDIT_PROFILE_OTP");

		sysSMSVO.setSmsbody(body);

		return sysSMSVO;
	}

	@Override
	public SysSMSVO getTicketSms(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysBookingDetailsVO sysBookingDetailsVO,
			String mAX_SMS_TRY_ALLOWED) {

		long ticketCost = sysBookingDetailsVO.getBookingVO().getTicketprice();
		long bookingFee = sysBookingDetailsVO.getBookingVO().getServicetax()
				+ sysBookingDetailsVO.getBookingVO().getConv();
		long fnbPrice = sysBookingDetailsVO.getFnBVO().getFb_totalprice();
		long discount = sysBookingDetailsVO.getBookingVO().getDiscount();
		long finalCost = ticketCost + bookingFee + fnbPrice;

		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setBookingid(String.valueOf(sysBookingDetailsVO.getBookingid()));
		sysSMSVO.setType("TICKET_BOOKING");

		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm");

		sysSMSVO.setMobilenum(sysBookingDetailsVO.getSysActualUserVO()
				.getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		sysSMSVO.setTimestamp(new Date());

		SMS_BODY = SMS_BODY.replace("<NO_SEATS>", String
				.valueOf(sysBookingDetailsVO.getBookingVO().getNumofseats()));
		SMS_BODY = SMS_BODY.replace("<SEATS>", sysBookingDetailsVO
				.getBookingVO().getSeats());
		SMS_BODY = SMS_BODY.replace(
				"<SHOW_DATE>",
				formatter.format(sysBookingDetailsVO.getBookingVO()
						.getShowtime()) + " hrs");
		SMS_BODY = SMS_BODY.replace("<CINEMA_HALL>", sysBookingDetailsVO
				.getBookingVO().getCinemaname());
		String QRCODE = "";
		QRCODE = SERVER_URL1 + "getbookingqr?bookingid="
				+ sysBookingDetailsVO.getBookingid() + "&cinemacode="
				+ sysBookingDetailsVO.getBookingVO().getCinemacode()
				+ "&swidth=700";

		
		if (sysBookingDetailsVO.getBookingVO().getEticket().equalsIgnoreCase("NOET")){
			if (sysBookingDetailsVO.getKioskid() == null) {
				SMS_BODY = SMS_BODY.replace("<SMS_KIOSK_LINE>", "");
				SMS_BODY = SMS_BODY.replace("<SMS_LINE_2>", "");
				
			} else {
			SMS_BODY = SMS_BODY.replace("<SMS_KIOSK_LINE>", SMS_KIOSK_LINE1);
			SMS_BODY = SMS_BODY.replace("<KIOSK_CODE>",
					sysBookingDetailsVO.getKioskid());
			SMS_BODY = SMS_BODY.replace("<SMS_LINE_2>", SMS_LINE_NOET);
			}
		}else{
			SMS_BODY = SMS_BODY.replace("<SMS_KIOSK_LINE>", SMS_KIOSK_LINE2);
			SMS_BODY = SMS_BODY.replace("<SMS_LINE_2>", SMS_LINE_ET+ " " +QRCODE);
		}
		
		if (sysBookingDetailsVO.getKioskid() == null) {
			SMS_BODY = SMS_BODY.replace("<SMS_KIOSK_LINE>", "");
			SMS_BODY = SMS_BODY.replace("<SMS_LINE_2>", "");
			
		} else {
			if (sysBookingDetailsVO.getBookingVO().getEticket().equalsIgnoreCase("NOET")){
				SMS_BODY = SMS_BODY.replace("<SMS_KIOSK_LINE>", SMS_KIOSK_LINE1);
				SMS_BODY = SMS_BODY.replace("<KIOSK_CODE>",
						sysBookingDetailsVO.getKioskid());
				SMS_BODY = SMS_BODY.replace("<SMS_LINE_2>", SMS_LINE_NOET);
			}else{
				SMS_BODY = SMS_BODY.replace("<SMS_KIOSK_LINE>", SMS_KIOSK_LINE2);
				SMS_BODY = SMS_BODY.replace("<SMS_LINE_2>", SMS_LINE_ET+ " " +QRCODE);
			}
		}

		String strTotal = String.valueOf(((float) finalCost) / 100);
		int lenString = strTotal.substring(strTotal.indexOf(".")).length();
		if (lenString == 2) {
			strTotal = strTotal + "0";
		}
	
		SMS_BODY = SMS_BODY.replace("<MOVIE_NAME>", sysBookingDetailsVO
				.getBookingVO().getMoviename());
		SMS_BODY = SMS_BODY.replace("<LANGUAGE>", sysBookingDetailsVO
				.getBookingVO().getMlanguage());
		SMS_BODY = SMS_BODY.replace("<CENSOR>", sysBookingDetailsVO.getBookingVO().getMcensor());
		SMS_BODY = SMS_BODY.replace("<TOTAL_AMOUNT>", " " + strTotal);
		
		
		SMS_BODY = SMS_BODY.replace("<ORDER_ID>",sysBookingDetailsVO.getBookingid());
		SMS_BODY = SMS_BODY.replace("<AUDI>", sysBookingDetailsVO
				.getBookingVO().getAudi());
		
		sysSMSVO.setSmsbody(SMS_BODY);
		sysSMSVO.setTimestamp(new Date());

		return sysSMSVO;

	}

	@Override
	public SysSMSVO getPrebookActualBookingSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysBookingDetailsVO sysBookingDetailsVO,
			SysPrefilmBookingVO sysPrefilmBookingVO,
			String mAX_SMS_TRY_ALLOWED, FilmVO filmVO) {

		long ticketCost = sysBookingDetailsVO.getBookingVO().getTicketprice();
		long bookingFee = sysBookingDetailsVO.getBookingVO().getServicetax()
				+ sysBookingDetailsVO.getBookingVO().getConv();
		long fnbPrice = sysBookingDetailsVO.getFnBVO().getFb_totalprice();
		long discount = sysBookingDetailsVO.getBookingVO().getDiscount();
		long finalCost = ticketCost + bookingFee + fnbPrice - discount;

		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setBookingid(String.valueOf(sysBookingDetailsVO.getBookingid()));
		sysSMSVO.setType("PREBOOK_ACTUALBOOK");

		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
		SimpleDateFormat formatter1 = new SimpleDateFormat("MMM dd");

		sysSMSVO.setMobilenum(sysBookingDetailsVO.getSysActualUserVO()
				.getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		sysSMSVO.setTimestamp(new Date());

		SMS_BODY = SMS_BODY.replace("<NO_SEATS>", String
				.valueOf(sysBookingDetailsVO.getBookingVO().getNumofseats()));
		SMS_BODY = SMS_BODY.replace("<SEATS>", sysBookingDetailsVO
				.getBookingVO().getSeats());
		SMS_BODY = SMS_BODY.replace(
				"<SHOW_DATE>",
				formatter.format(sysBookingDetailsVO.getBookingVO()
						.getShowtime())
						+ " on "
						+ formatter1.format(sysBookingDetailsVO.getBookingVO()
								.getShowtime()));
		SMS_BODY = SMS_BODY.replace("<CINEMA_HALL>", sysBookingDetailsVO
				.getBookingVO().getCinemaname());
		SMS_BODY = SMS_BODY.replace("<AUDI>", sysBookingDetailsVO
				.getBookingVO().getAudi());
		SMS_BODY = SMS_BODY.replace("<PREBOOK_ID>",
				sysPrefilmBookingVO.getPkBookingId());
		long refundAmount = sysPrefilmBookingVO.getTotalAmount()
				- sysBookingDetailsVO.getBookingVO().getTotalticketprice();

		String strTA = String.valueOf(((double) refundAmount) / 100);
		int lenTA = strTA.substring(strTA.indexOf(".")).length();
		if (lenTA == 2) {
			strTA = strTA + "0";
		}

		SMS_BODY = SMS_BODY.replace("<REFUND_AMOUNT>", strTA);

		if (sysBookingDetailsVO.getKioskid().equals("")) {
			SMS_BODY = SMS_BODY.replace("<SMS_KIOSK_LINE>", "");
		} else {
			SMS_BODY = SMS_BODY.replace("<SMS_KIOSK_LINE>", SMS_KIOSK_LINE);
			SMS_BODY = SMS_BODY.replace("<KIOSK_CODE>",
					sysBookingDetailsVO.getKioskid());
		}

		String strTotal = String.valueOf(((float) finalCost) / 100);
		int lenString = strTotal.substring(strTotal.indexOf(".")).length();
		if (lenString == 2) {
			strTotal = strTotal + "0";
		}

		SMS_BODY = SMS_BODY.replace("<MOVIE_NAME>", sysBookingDetailsVO
				.getBookingVO().getMoviename());
		SMS_BODY = SMS_BODY.replace("<LANGUAGE>", sysBookingDetailsVO
				.getBookingVO().getMlanguage());
		SMS_BODY = SMS_BODY.replace("<CENSOR>", filmVO.getMcensor());
		SMS_BODY = SMS_BODY.replace("<TOTAL_AMOUNT>", strTotal);
		SMS_BODY = SMS_BODY.replace("<ORDER_ID>",
				sysBookingDetailsVO.getBookingid());

		sysSMSVO.setSmsbody(SMS_BODY);
		sysSMSVO.setTimestamp(new Date());

		return sysSMSVO;

	}

	@Override
	public SysSMSVO getEwalletSms(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysBookingDetailsVO sysBookingDetailsVO, SysEWalletVO sysEWalletVO,
			String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setMobilenum(sysBookingDetailsVO.getSysActualUserVO()
				.getMobileNo());

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();

		sysSMSVO.setSmsbody(SMS_BODY);
		sysSMSVO.setTimestamp(new Date());

		sysSMSVO.setBookingid(sysBookingDetailsVO.getBookingid());
		sysSMSVO.setType("EWALLET_ADDED");

		return sysSMSVO;
	}

	@Override
	public SysSMSVO getFNBSuccessSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysFAndBPurchaseDetailVO objFandBNew, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setMobilenum(objFandBNew.getSysActualUserVO().getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));

		sysSMSVO.setBookingid(String.valueOf(objFandBNew.getPktransid()));
		sysSMSVO.setType("FANDB_BOOK");

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		sysSMSVO.setTimestamp(new Date());

		SMS_BODY = SMS_BODY.replace("<CINEMA>", objFandBNew.getCinemaname());
		SMS_BODY = SMS_BODY.replace("<ORDER_ID>", objFandBNew.getBookingid());

		sysSMSVO.setSmsbody(SMS_BODY);
		sysSMSVO.setTimestamp(new Date());

		return sysSMSVO;
	}

	@Override
	public SysSMSVO getGiftCardSuccessSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysGiftCardPurchaseVO objGCPurchaseNew, String mAX_SMS_TRY_ALLOWED) {
		// Order successful for $GC_NUM$ gift cards worth $GC_DENO$ each. You
		// shall receive them within 5 working days. TotalAmnt paid:
		// $TOTAL_AMOUNT$
		SysSMSVO sysSMSVO = new SysSMSVO();

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();

		sysSMSVO.setBookingid(String.valueOf(objGCPurchaseNew.getGcOrderId()));
		sysSMSVO.setType("GC_PURCHASE");

		int quantity = 0;

		String[] arrQuantity = objGCPurchaseNew.getQuantity().split(",");
		for (String strQuantity : arrQuantity) {
			quantity += Integer.parseInt(strQuantity);
		}

		String body = SMS_BODY.replace("<GC_NUM>", String.valueOf(quantity));

		String strTotal = String.valueOf(((float) objGCPurchaseNew
				.getTotalAmount()) / 100);
		int lenString = strTotal.substring(strTotal.indexOf(".")).length();
		if (lenString == 2) {
			strTotal = strTotal + "0";
		}
		// body = body.replace("<GC_DENO>", objGCPurchaseNew.getDenomination());

		body = body.replace("<TOTAL_AMOUNT>", "Rs " + strTotal);

		sysSMSVO.setSmsbody(body);
		sysSMSVO.setMobilenum(objGCPurchaseNew.getSysActualUser().getMobileNo());

		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));
		sysSMSVO.setTimestamp(new Date());

		return sysSMSVO;

	}

	@Override
	public SysSMSVO getPrebookingSuccessSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysPrefilmBookingVO objPreBookingNew, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setMobilenum(objPreBookingNew.getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));
		sysSMSVO.setTimestamp(new java.util.Date());

		sysSMSVO.setBookingid(String.valueOf(objPreBookingNew.getPkBookingId()));
		sysSMSVO.setType("PREFILM_PREBOOK");

		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd");

		SimpleDateFormat formatter1 = new SimpleDateFormat("dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("MMM");

		String[] arrDates = objPreBookingNew.getDatesSelected().split(",");
		String dateToDisplay = "";

		if (arrDates.length > 0) {
			for (String strDate : arrDates) {
				long milliSeconds = Long.parseLong(strDate);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(milliSeconds);

				// String[] arrDateSel = strDate.split("-");
				if (dateToDisplay.equals("")) {

					// dateToDisplay = strDate;
					dateToDisplay = formatter.format(calendar.getTime());

				} else {
					if (dateToDisplay.contains(formatter2.format(calendar
							.getTime()))) {
						dateToDisplay += ","
								+ formatter1.format(calendar.getTime());
					} else {
						dateToDisplay += ","
								+ formatter.format(calendar.getTime());
					}
				}

			}

		}

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		String[] arrCineName = objPreBookingNew.getCinemaNameSelected().split("#");
		String strCineNames = "";
		for (String strCine : arrCineName) {
			if (strCineNames.equals("")) {
				strCineNames = strCine;
			} else {
				strCineNames += " or " + strCine;
			}

		}
		String strTotal = String.valueOf(((float) objPreBookingNew
				.getTotalAmount()) / 100);
		int lenString = strTotal.substring(strTotal.indexOf(".")).length();
		if (lenString == 2) {
			strTotal = strTotal + "0";
		}

		String body = SMS_BODY.replace("<MOVIE_NAME>",
				objPreBookingNew.getFilmName());
		body = body.replace("<PRE_BOOKING_ID>",
				objPreBookingNew.getPkBookingId());
		body = body.replace("<CINEMA_HALL>", strCineNames);
		body = body.replace("<SHOW_DATES>", dateToDisplay);
		body = body.replace("<SHOW_TIME>", objPreBookingNew.getShowtime());
		body = body.replace("<TICKET_NUMBER>",
				String.valueOf(objPreBookingNew.getNoSeats()));

		body = body.replace("<TOTAL_AMOUNT>", strTotal);

		sysSMSVO.setSmsbody(body);

		return sysSMSVO;

	}

	@Override
	public SysSMSVO getPrebookingRescheduleSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysPrefilmBookingVO objPreUsers, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setMobilenum(objPreUsers.getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));

		sysSMSVO.setBookingid(String.valueOf(objPreUsers.getPkBookingId()));
		sysSMSVO.setType("PREFILM_RESCHEDULE");

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();

		String body = SMS_BODY.replace("<MOVIE_NAME>",
				objPreUsers.getFilmName());

		sysSMSVO.setSmsbody(body);

		return sysSMSVO;
	}

	@Override
	public SysSMSVO getGiftCardDispatchSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysGiftCardPurchaseVO objGCPurchaseNew, String mAX_SMS_TRY_ALLOWED) {

		// Your order for $GC_NUM$ gift cards has been dispatched on
		// $DISPATCH_DATE$. For queries, call 08800900009
		SysSMSVO sysSMSVO = new SysSMSVO();
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd");

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();

		sysSMSVO.setBookingid(String.valueOf(objGCPurchaseNew.getGcOrderId()));
		sysSMSVO.setType("GC_DISPATCH");

		int quantity = 0;

		String[] arrQuantity = objGCPurchaseNew.getQuantity().split(",");
		for (String strQuantity : arrQuantity) {
			quantity += Integer.parseInt(strQuantity);
		}

		String body = SMS_BODY.replace("<GC_NUM>", String.valueOf(quantity));
		body = body.replace("<DISPATCH_DATE>",
				formatter.format(new java.util.Date()));

		sysSMSVO.setSmsbody(body);
		sysSMSVO.setMobilenum(objGCPurchaseNew.getSysActualUser().getMobileNo());
		sysSMSVO.setTimestamp(new Date());

		return sysSMSVO;

	}

	@Override
	public SysSMSVO getBulkBookingSuccessSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysBulkBookingVO objBulk, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setBookingid(String.valueOf(objBulk.getPkBookingId()));
		sysSMSVO.setType("BULK_BOOKING");

		sysSMSVO.setMobilenum(objBulk.getSysActualUser().getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		sysSMSVO.setTimestamp(new Date());

		sysSMSVO.setSmsbody(SMS_BODY);

		return sysSMSVO;
	}

	@Override
	public SysSMSVO getBookingFnBSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysBookingDetailsVO sysBookingDetailsVO,
			String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setBookingid(String.valueOf(sysBookingDetailsVO.getBookingid()));
		sysSMSVO.setType("TICKET_FNB_BOOKING");

		sysSMSVO.setMobilenum(sysBookingDetailsVO.getSysActualUserVO()
				.getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		sysSMSVO.setTimestamp(new Date());

		SMS_BODY = SMS_BODY.replace("<ORDER_ID>",
				sysBookingDetailsVO.getBookingid());

		sysSMSVO.setSmsbody(SMS_BODY);
		sysSMSVO.setTimestamp(new Date());

		return sysSMSVO;

	}
*//*
	@Override
	public SysSMSVO getPreBookingRefundSuccessSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysPrefilmBookingVO objPreBookingNew, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setBookingid(objPreBookingNew.getPkBookingId());
		sysSMSVO.setType("PREBOOK_REFUND_NOMATCH");

		sysSMSVO.setMobilenum(objPreBookingNew.getSysActualUser().getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));
		sysSMSVO.setTimestamp(new java.util.Date());

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		String body = SMS_BODY.replace("<MOVIE_NAME>",
				objPreBookingNew.getFilmName());

		sysSMSVO.setSmsbody(body);

		return sysSMSVO;

	}

	@Override
	public SysSMSVO getPrebookingRefundUserRequestSms(
			SysEmailSmsTemplateVO objEmailSms, SysPrefilmBookingVO objPre,
			String mAX_SMS_TRY_ALLOWED) {

		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setBookingid(objPre.getPkBookingId());
		sysSMSVO.setType("PREBOOK_REFUND");

		sysSMSVO.setMobilenum(objPre.getSysActualUser().getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));
		sysSMSVO.setTimestamp(new java.util.Date());

		String SMS_BODY = objEmailSms.getSmsTemplate();
		String body = SMS_BODY.replace("<MOVIE_NAME>", objPre.getFilmName());
		body = body.replace("<PREBOOK_ID>", " " + objPre.getPkBookingId());

		String strTA = String
				.valueOf((((float) objPre.getTotalAmount()) / 100));
		int lenTA = strTA.substring(strTA.indexOf(".")).length();
		if (lenTA == 2) {
			strTA = strTA + "0";
		}

		body = body.replace("<REFUND_AMOUNT>", "Rs " + strTA);

		sysSMSVO.setSmsbody(body);

		return sysSMSVO;
	}

	@Override
	public SysSMSVO getRegisterSms(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysActualUserVO sysActualUserVO, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setMobilenum(sysActualUserVO.getMobileNo());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));
		sysSMSVO.setTimestamp(new java.util.Date());

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		// String body = SMS_BODY.replace("<OTP>",sysActualUserVO.getOpt());

		sysSMSVO.setSmsbody(SMS_BODY);

		sysSMSVO.setBookingid(String.valueOf(sysActualUserVO.getPkProfilerId()));
		sysSMSVO.setType("REGISTRATION");

		return sysSMSVO;

	}

	@Override
	public SysSMSVO getFeedbackSms(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysFeedbackVO objFeedback, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setBookingid(String.valueOf(""));
		sysSMSVO.setType("FEEDBACK");

		sysSMSVO.setMobilenum(objFeedback.getMobile());
		sysSMSVO.setRetrycount(Integer.parseInt(mAX_SMS_TRY_ALLOWED));
		sysSMSVO.setTimestamp(new java.util.Date());

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();
		// String body = SMS_BODY.replace("<OTP>",sysActualUserVO.getOpt());

		sysSMSVO.setSmsbody(SMS_BODY);

		return sysSMSVO;
	}

	@Override
	public SysSMSVO getFoodEwalletSms(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysFAndBPurchaseDetailVO fAndBPurchaseDetailVO,
			SysEWalletVO sysEWalletVO, String mAX_SMS_TRY_ALLOWED) {
		SysSMSVO sysSMSVO = new SysSMSVO();

		sysSMSVO.setMobilenum(fAndBPurchaseDetailVO.getSysActualUserVO()
				.getMobileNo());

		String SMS_BODY = sysEmailSmsTemplateVO.getSmsTemplate();

		sysSMSVO.setSmsbody(SMS_BODY);
		sysSMSVO.setTimestamp(new Date());

		sysSMSVO.setBookingid(fAndBPurchaseDetailVO.getPktransid());
		sysSMSVO.setType("FOOD_EWALLET_ADDED");

		return sysSMSVO;
	}
*/
}
