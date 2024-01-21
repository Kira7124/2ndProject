function () {

    initializeStatusButtons();

    // 버튼 클릭 시 상태 업데이트
    $('.statusButton').on('click', function () {
        var button = $(this);
        var product_code = button.closest('tr').find('.product_code').val();
        updateStatus(button, product_code);
    });
});

function initializeStatusButtons() {
    // 각 행의 상태를 가져와서 적용
    $('.statusButton').each(function () {
        var button = $(this);
        var product_code = button.closest('tr').find('.product_code').val();
        updateButtonStatus(button, product_code);
    });
}

function updateStatus(button, product_code) {
    // 서버에 상태 업데이트 요청
    $.ajax({
        type: 'POST',
        url: 'updateStatus',
        data: {
            product_code: product_code,
            status: 'complete'
        },
        success: function (response) {
            if (response > 0) {
                // 성공 시 해당 버튼 상태 업데이트
                updateButtonStatus(button, product_code);
            } else {
                alert('상태 업데이트 실패');
            }
        },
        error: function () {
            alert('AJAX 오류 발생');
        }
    });
}

function updateButtonStatus(button, product_code) {
    // 서버에 상태 요청
    $.ajax({
        type: 'GET',
        url: 'getOrderStatus',
        data: {
            product_code: product_code
        },
        success: function (response) {
            var status = response[0].status; // 가정: purchaselist에 하나의 항목만 있는 경우

            // 받아온 상태에 따라 해당 버튼 업데이트
            if (status === 'complete') {
                button.removeClass('btn-primary').addClass('btn-success').text('완료');
                button.data('status', 'complete'); // 상태 업데이트
            } else {
                button.removeClass('btn-success').addClass('btn-primary').text('대기');
                button.data('status', 'waiting'); // 상태 업데이트
            }
        },
        error: function () {
            alert('상태 조회 오류 발생');
        }
    });
}	








function updateStatus(button) {
			var product_code = $(button).closest('tr').find('.product_code')
					.val();
			var currentStatus = $(button).data('status');

			$.ajax({
				type : 'POST',
				url : 'updateStatus',
				data : {
					product_code : product_code,
					status : 'complete'
				},
				success : function(response) {
					if (response > 0) {
						// 성공 시 버튼 상태를 업데이트합니다.
						$(button).data('status', 'complete');
						$(button).removeClass('btn-primary').addClass(
								'btn-success').text('완료');
					} else {
						// 실패 시 상태를 원래대로 돌려놓습니다.
						$(button).data('status', 'waiting');
						$(button).removeClass('btn-success').addClass(
								'btn-primary').text('대기');
						alert('상태 업데이트 실패');
					}
				},
				error : function() {
					// 에러 시 상태를 원래대로 돌려놓습니다.
					$(button).data('status', 'waiting');
					$(button).removeClass('btn-success')
							.addClass('btn-primary').text('대기');
					alert('AJAX 오류 발생');
				}
			});
		} 
