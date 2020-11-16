<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">

function totalSum() {
	var totalSum = $(".totalSum");
	var Price = 0;
	for(var i = 0; i < totalSum.length; i++) {
		Price += parseInt(totalSum[i].innerText);
	}
	$("#total").text("총합계 : " + Price);
}
	$(document).ready(function () {
		
	
		$("#allCheck").click(function () {
			var result = this.checked;
			$(".check").each(function (idx, data) {
				this.checked = result;
			});
		});
		
		$(".delBtn").on("click", function () {
			var num = $(this).attr("data-num");
			var xxx = $(this);
			$.ajax({
				url: "loginCheck/cartDel",
				type: "get",
				dataType: "text",
				data: {
					num: num
				},
				success: function(data, status, xhr) {
					console.log("success");
					//dom삭제
					xxx.parents().filter("tr").remove(); //tr테그 삭제 비동기 처리
					totalSum();
				},
				error: function(xhr, status, error) {
					console.log(error);
				}
			}); //end ajax
		});
		
		$(".updateBtn").on("click", function () {
			var num = $(this).attr("data-num"); //번호 가져오기 ${x.num}
			var gAmount = $("#cartAmount"+num).val(); //수량 가져오기
			var gPrice = $(this).attr("data-price"); //가격 가져오기
			$.ajax({
				url:'loginCheck/cartUpdate',
				type:'get',
				dataType:'text',
				data:{
					num:num, //카트번호
					gAmount:gAmount //수정한 수량 전송
				},
				success:function(data, status, xhr) {
					var total = parseInt(gAmount) * parseInt(gPrice);
					$("#sum"+num).text(total);
					totalSum();
				},
				error:function(xhr, status, error) {
					
				}
			});
		
		});
		
		// 전체삭제1
		$("#delAllCart").on("click", function () {
			var num = [];
			$("input:checkbox[name='check']:checked").each(function (idx, ele) {
				num[idx] = $(this).val();
			});
			location.href="CartDelAllServlet?data="+num;
		});
		
		// 전체삭제2
		$("#delAllCart2").on("click", function () {
			$("form").attr("action", "CartDelAllServlet2");
			$("form").submit(); // trigger
		});
		
		$(".orderBtn").on("click", function () {
			var num = $(this).attr("data-xxx");
			location.href="CartOrderConfirmServlet?num="+num;
		})
		
		totalSum();
		
		
		
	});
	
</script>

<table width="90%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td height="30">
	</tr>
	<tr>
		<td colspan="5" class="td_default">&nbsp;&nbsp;&nbsp; <font
			size="5"><b>- 장바구니-</b></font> &nbsp;
		</td>
	</tr>
	<tr>
		<td height="15">
	</tr>
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
	<tr>
		<td height="7">
	</tr>
	<tr>
		<td class="td_default" align="center">
		<input type="checkbox" name="allCheck" id="allCheck"> <strong>전체선택</strong></td>
		<td class="td_default" align="center"><strong>주문번호</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>상품정보</strong></td>
		<td class="td_default" align="center"><strong>판매가</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>수량</strong></td>
		<td class="td_default" align="center"><strong>합계</strong></td>
		<td></td>
	</tr>

	<tr>
		<td height="7">
	</tr>
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
	<form name="myForm">
	<c:forEach var="x" items="${cartList}" varStatus="status">	    
		<tr>
			<td class="td_default" width="80">
			<!-- checkbox는 체크된 값만 서블릿으로 넘어간다. 따라서 value에 삭제할 num값을 설정한다. -->
			<input type="checkbox" name="check" id="check81" class="check" value="${x.num}">
			</td>
			<td class="td_default" width="80">${x.num}</td>
			<td class="td_default" width="80">
			<img src="images/items/${x.gImage}.gif" border="0" align="center" width="80" />
			</td>
			<td class="td_default" width="300" style='padding-left: 30px'>
			${x.gName}
				<br> <font size="2" color="#665b5f">[옵션 : 사이즈(${x.gSize})
					, 색상(${x.gColor}))]
			</font></td>
			<td class="td_default" align="center" width="110">
			${x.gPrice}
			</td>
			<td class="td_default" align="center" width="90">
			<input class="input_default" type="text" name="cartAmount" id="cartAmount${x.num}" style='text-align: right' maxlength="3"
				size="2" value="${x.gAmount}"></input></td>
			<td><input type="button" value="수정"
				class="updateBtn" data-num="${x.num}" data-price="${x.gPrice}"/>
				</td>
			<td class="td_default" align="center" width="80"
				style="padding-left: 5px"><span id="sum${x.num}" class="totalSum">
				${x.gPrice * x.gAmount}
				</span></td>
			<td><input type="button" value="주문" class="orderBtn" data-xxx="${x.num}"></td>
			<td class="td_default" align="center" width="30" style='padding-left: 10px'>
				<input type="button" value="삭제" id="xx${x.num}" class="delBtn" data-num="${x.num}">
			</td>
			<td height="10"></td>
		</tr>
	</c:forEach>
	</form>
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
	<tr>
		<td height="30">
		<span id="total"></span>
	</tr>
	<tr>
		<td align="center" colspan="5"><a class="a_black"
			href="javascript:orderAllConfirm(myForm)"> 전체 주문하기 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
			<a class="a_black" href="#" id="delAllCart"> 전체 삭제하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="#" id="delAllCart2"> 전체 삭제하기2 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="index.jsp"> 계속 쇼핑하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td height="20">
	</tr>

</table>
    