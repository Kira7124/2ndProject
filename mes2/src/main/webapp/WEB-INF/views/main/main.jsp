<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/css/main/main.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
<title>MainPage</title>
</head>
<body>
	<section class="content">
		<main>
			<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>Recent Orders</h3>
						<i class="fas fa-search"></i> <i class="fas fa-filter"></i>
					</div>

					<table>
						<thead>
							<tr>
								<th>User</th>
								<th>Order Date</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status pending">Pending</span></td>
							</tr>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status pending">Pending</span></td>
							</tr>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status process">Process</span></td>
							</tr>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status process">Process</span></td>
							</tr>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status complete">Complete</span></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="chart">
					<div class="head">
						<h3>Sales Report</h3>
						<i class="fas fa-search"></i> <i class="fas fa-filter"></i>
					</div>
					<div>
						<canvas id="sales-chart" width="432" height="216"
							style="display: block; height: 173px; width: 346px;"
							class="chartjs-render-monitor"></canvas>
					</div>
				</div>

				<div class="todo">
					<div class="head">

						<h3>Todos</h3>
						<i class="fas fa-plus"></i> <i class="fas fa-filter"></i>
					</div>

					<ul class="todo-list">
						<li class="not-completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
						<li class="not-completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
						<li class="completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
						<li class="completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
						<li class="completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
					</ul>
				</div>

				<div class="calendar">
					<div class="head">
						<h3>Calendar</h3>
					</div>
					<div id="calendar" class="fc fc-unthemed fc-ltr">
						<div class="fc-toolbar fc-header-toolbar">
							<div class="fc-center">
								<h2>December 2023</h2>
							</div>
							<div class="fc-clear"></div>
						</div>
						<div class="fc-view-container" style="">
							<div class="fc-view fc-month-view fc-basic-view" style="">
								<table class="calendar-table">
									<thead class="fc-head">
										<tr>
											<td class="fc-head-container fc-widget-header"><div
													class="fc-row fc-widget-header">
													<table>
														<thead>
															<tr>
																<th class="fc-day-header fc-widget-header fc-sun"><span>Sun</span></th>
																<th class="fc-day-header fc-widget-header fc-mon"><span>Mon</span></th>
																<th class="fc-day-header fc-widget-header fc-tue"><span>Tue</span></th>
																<th class="fc-day-header fc-widget-header fc-wed"><span>Wed</span></th>
																<th class="fc-day-header fc-widget-header fc-thu"><span>Thu</span></th>
																<th class="fc-day-header fc-widget-header fc-fri"><span>Fri</span></th>
																<th class="fc-day-header fc-widget-header fc-sat"><span>Sat</span></th>
															</tr>
														</thead>
													</table>
												</div></td>
										</tr>
									</thead>
									<tbody class="fc-body">
										<tr>
											<td class="fc-widget-content"><div
													class="fc-scroller fc-day-grid-container"
													style="overflow: hidden; height: 619px;">
													<div class="fc-day-grid fc-unselectable">
														<div class="fc-row fc-week fc-widget-content"
															style="height: 103px;">
															<div class="fc-bg">
																<table>
																	<tbody>
																		<tr>
																			<td
																				class="fc-day fc-widget-content fc-sun fc-other-month fc-past"
																				data-date="2023-11-26"></td>
																			<td
																				class="fc-day fc-widget-content fc-mon fc-other-month fc-past"
																				data-date="2023-11-27"></td>
																			<td
																				class="fc-day fc-widget-content fc-tue fc-other-month fc-past"
																				data-date="2023-11-28"></td>
																			<td
																				class="fc-day fc-widget-content fc-wed fc-other-month fc-past"
																				data-date="2023-11-29"></td>
																			<td
																				class="fc-day fc-widget-content fc-thu fc-other-month fc-past"
																				data-date="2023-11-30"></td>
																			<td class="fc-day fc-widget-content fc-fri fc-past"
																				data-date="2023-12-01"></td>
																			<td class="fc-day fc-widget-content fc-sat fc-past"
																				data-date="2023-12-02"></td>
																		</tr>
																	</tbody>
																</table>
															</div>
															<div class="fc-content-skeleton">
																<table>
																	<thead>
																		<tr>
																			<td class="fc-day-top fc-sun fc-other-month fc-past"
																				data-date="2023-11-26"><span
																				class="fc-day-number">26</span></td>
																			<td class="fc-day-top fc-mon fc-other-month fc-past"
																				data-date="2023-11-27"><span
																				class="fc-day-number">27</span></td>
																			<td class="fc-day-top fc-tue fc-other-month fc-past"
																				data-date="2023-11-28"><span
																				class="fc-day-number">28</span></td>
																			<td class="fc-day-top fc-wed fc-other-month fc-past"
																				data-date="2023-11-29"><span
																				class="fc-day-number">29</span></td>
																			<td class="fc-day-top fc-thu fc-other-month fc-past"
																				data-date="2023-11-30"><span
																				class="fc-day-number">30</span></td>
																			<td class="fc-day-top fc-fri fc-past"
																				data-date="2023-12-01"><span
																				class="fc-day-number">1</span></td>
																			<td class="fc-day-top fc-sat fc-past"
																				data-date="2023-12-02"><span
																				class="fc-day-number">2</span></td>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td class="fc-event-container"><a
																				class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable"><div
																						class="fc-content">
																						<span class="fc-time">12a</span> <span
																							class="fc-title">All Day Event</span>
																					</div></a></td>
																			<td></td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
														<div class="fc-row fc-week fc-widget-content"
															style="height: 103px;">
															<div class="fc-bg">
																<table>
																	<tbody>
																		<tr>
																			<td class="fc-day fc-widget-content fc-sun fc-past"
																				data-date="2023-12-03"></td>
																			<td class="fc-day fc-widget-content fc-mon fc-past"
																				data-date="2023-12-04"></td>
																			<td class="fc-day fc-widget-content fc-tue fc-past"
																				data-date="2023-12-05"></td>
																			<td class="fc-day fc-widget-content fc-wed fc-past"
																				data-date="2023-12-06"></td>
																			<td class="fc-day fc-widget-content fc-thu fc-past"
																				data-date="2023-12-07"></td>
																			<td class="fc-day fc-widget-content fc-fri fc-past"
																				data-date="2023-12-08"></td>
																			<td class="fc-day fc-widget-content fc-sat fc-past"
																				data-date="2023-12-09"></td>
																		</tr>
																	</tbody>
																</table>
															</div>
															<div class="fc-content-skeleton">
																<table>
																	<thead>
																		<tr>
																			<td class="fc-day-top fc-sun fc-past"
																				data-date="2023-12-03"><span
																				class="fc-day-number">3</span></td>
																			<td class="fc-day-top fc-mon fc-past"
																				data-date="2023-12-04"><span
																				class="fc-day-number">4</span></td>
																			<td class="fc-day-top fc-tue fc-past"
																				data-date="2023-12-05"><span
																				class="fc-day-number">5</span></td>
																			<td class="fc-day-top fc-wed fc-past"
																				data-date="2023-12-06"><span
																				class="fc-day-number">6</span></td>
																			<td class="fc-day-top fc-thu fc-past"
																				data-date="2023-12-07"><span
																				class="fc-day-number">7</span></td>
																			<td class="fc-day-top fc-fri fc-past"
																				data-date="2023-12-08"><span
																				class="fc-day-number">8</span></td>
																			<td class="fc-day-top fc-sat fc-past"
																				data-date="2023-12-09"><span
																				class="fc-day-number">9</span></td>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
														<div class="fc-row fc-week fc-widget-content"
															style="height: 103px;">
															<div class="fc-bg">
																<table>
																	<tbody>
																		<tr>
																			<td class="fc-day fc-widget-content fc-sun fc-past"
																				data-date="2023-12-10"></td>
																			<td class="fc-day fc-widget-content fc-mon fc-past"
																				data-date="2023-12-11"></td>
																			<td class="fc-day fc-widget-content fc-tue fc-past"
																				data-date="2023-12-12"></td>
																			<td class="fc-day fc-widget-content fc-wed fc-past"
																				data-date="2023-12-13"></td>
																			<td class="fc-day fc-widget-content fc-thu fc-past"
																				data-date="2023-12-14"></td>
																			<td class="fc-day fc-widget-content fc-fri fc-past"
																				data-date="2023-12-15"></td>
																			<td
																				class="fc-day fc-widget-content fc-sat fc-today fc-state-highlight"
																				data-date="2023-12-16"></td>
																		</tr>
																	</tbody>
																</table>
															</div>
															<div class="fc-content-skeleton">
																<table>
																	<thead>
																		<tr>
																			<td class="fc-day-top fc-sun fc-past"
																				data-date="2023-12-10"><span
																				class="fc-day-number">10</span></td>
																			<td class="fc-day-top fc-mon fc-past"
																				data-date="2023-12-11"><span
																				class="fc-day-number">11</span></td>
																			<td class="fc-day-top fc-tue fc-past"
																				data-date="2023-12-12"><span
																				class="fc-day-number">12</span></td>
																			<td class="fc-day-top fc-wed fc-past"
																				data-date="2023-12-13"><span
																				class="fc-day-number">13</span></td>
																			<td class="fc-day-top fc-thu fc-past"
																				data-date="2023-12-14"><span
																				class="fc-day-number">14</span></td>
																			<td class="fc-day-top fc-fri fc-past"
																				data-date="2023-12-15"><span
																				class="fc-day-number">15</span></td>
																			<td
																				class="fc-day-top fc-sat fc-today fc-state-highlight"
																				data-date="2023-12-16"><span
																				class="fc-day-number">16</span></td>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td class="fc-event-container" colspan="3"><a
																				class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable"><div
																						class="fc-content">
																						<span class="fc-time">12a</span> <span
																							class="fc-title">Long Event</span>
																					</div></a></td>
																			<td></td>
																			<td></td>
																			<td class="fc-event-container"><a
																				class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable"><div
																						class="fc-content">
																						<span class="fc-time">10:30a</span> <span
																							class="fc-title">Meeting</span>
																					</div></a></td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
														<div class="fc-row fc-week fc-widget-content"
															style="height: 103px;">
															<div class="fc-bg">
																<table>
																	<tbody>
																		<tr>
																			<td class="fc-day fc-widget-content fc-sun fc-future"
																				data-date="2023-12-17"></td>
																			<td class="fc-day fc-widget-content fc-mon fc-future"
																				data-date="2023-12-18"></td>
																			<td class="fc-day fc-widget-content fc-tue fc-future"
																				data-date="2023-12-19"></td>
																			<td class="fc-day fc-widget-content fc-wed fc-future"
																				data-date="2023-12-20"></td>
																			<td class="fc-day fc-widget-content fc-thu fc-future"
																				data-date="2023-12-21"></td>
																			<td class="fc-day fc-widget-content fc-fri fc-future"
																				data-date="2023-12-22"></td>
																			<td class="fc-day fc-widget-content fc-sat fc-future"
																				data-date="2023-12-23"></td>
																		</tr>
																	</tbody>
																</table>
															</div>
															<div class="fc-content-skeleton">
																<table>
																	<thead>
																		<tr>
																			<td class="fc-day-top fc-sun fc-future"
																				data-date="2023-12-17"><span
																				class="fc-day-number">17</span></td>
																			<td class="fc-day-top fc-mon fc-future"
																				data-date="2023-12-18"><span
																				class="fc-day-number">18</span></td>
																			<td class="fc-day-top fc-tue fc-future"
																				data-date="2023-12-19"><span
																				class="fc-day-number">19</span></td>
																			<td class="fc-day-top fc-wed fc-future"
																				data-date="2023-12-20"><span
																				class="fc-day-number">20</span></td>
																			<td class="fc-day-top fc-thu fc-future"
																				data-date="2023-12-21"><span
																				class="fc-day-number">21</span></td>
																			<td class="fc-day-top fc-fri fc-future"
																				data-date="2023-12-22"><span
																				class="fc-day-number">22</span></td>
																			<td class="fc-day-top fc-sat fc-future"
																				data-date="2023-12-23"><span
																				class="fc-day-number">23</span></td>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td class="fc-event-container"><a
																				class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable"><div
																						class="fc-content">
																						<span class="fc-time">7p</span> <span
																							class="fc-title">Birthday Party</span>
																					</div></a></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
														<div class="fc-row fc-week fc-widget-content"
															style="height: 103px;">
															<div class="fc-bg">
																<table>
																	<tbody>
																		<tr>
																			<td class="fc-day fc-widget-content fc-sun fc-future"
																				data-date="2023-12-24"></td>
																			<td class="fc-day fc-widget-content fc-mon fc-future"
																				data-date="2023-12-25"></td>
																			<td class="fc-day fc-widget-content fc-tue fc-future"
																				data-date="2023-12-26"></td>
																			<td class="fc-day fc-widget-content fc-wed fc-future"
																				data-date="2023-12-27"></td>
																			<td class="fc-day fc-widget-content fc-thu fc-future"
																				data-date="2023-12-28"></td>
																			<td class="fc-day fc-widget-content fc-fri fc-future"
																				data-date="2023-12-29"></td>
																			<td class="fc-day fc-widget-content fc-sat fc-future"
																				data-date="2023-12-30"></td>
																		</tr>
																	</tbody>
																</table>
															</div>
															<div class="fc-content-skeleton">
																<table>
																	<thead>
																		<tr>
																			<td class="fc-day-top fc-sun fc-future"
																				data-date="2023-12-24"><span
																				class="fc-day-number">24</span></td>
																			<td class="fc-day-top fc-mon fc-future"
																				data-date="2023-12-25"><span
																				class="fc-day-number">25</span></td>
																			<td class="fc-day-top fc-tue fc-future"
																				data-date="2023-12-26"><span
																				class="fc-day-number">26</span></td>
																			<td class="fc-day-top fc-wed fc-future"
																				data-date="2023-12-27"><span
																				class="fc-day-number">27</span></td>
																			<td class="fc-day-top fc-thu fc-future"
																				data-date="2023-12-28"><span
																				class="fc-day-number">28</span></td>
																			<td class="fc-day-top fc-fri fc-future"
																				data-date="2023-12-29"><span
																				class="fc-day-number">29</span></td>
																			<td class="fc-day-top fc-sat fc-future"
																				data-date="2023-12-30"><span
																				class="fc-day-number">30</span></td>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td class="fc-event-container"><a
																				class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable"
																				href="http://google.com/"><div
																						class="fc-content">
																						<span class="fc-time">12a</span> <span
																							class="fc-title">Click for Google</span>
																					</div></a></td>
																			<td></td>
																			<td class="fc-event-container"><a
																				class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable"><div
																						class="fc-content">
																						<span class="fc-time">12p</span> <span
																							class="fc-title">Lunch</span>
																					</div></a></td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
														<div class="fc-row fc-week fc-widget-content"
															style="height: 104px;">
															<div class="fc-bg">
																<table>
																	<tbody>
																		<tr>
																			<td class="fc-day fc-widget-content fc-sun fc-future"
																				data-date="2023-12-31"></td>
																			<td
																				class="fc-day fc-widget-content fc-mon fc-other-month fc-future"
																				data-date="2024-01-01"></td>
																			<td
																				class="fc-day fc-widget-content fc-tue fc-other-month fc-future"
																				data-date="2024-01-02"></td>
																			<td
																				class="fc-day fc-widget-content fc-wed fc-other-month fc-future"
																				data-date="2024-01-03"></td>
																			<td
																				class="fc-day fc-widget-content fc-thu fc-other-month fc-future"
																				data-date="2024-01-04"></td>
																			<td
																				class="fc-day fc-widget-content fc-fri fc-other-month fc-future"
																				data-date="2024-01-05"></td>
																			<td
																				class="fc-day fc-widget-content fc-sat fc-other-month fc-future"
																				data-date="2024-01-06"></td>
																		</tr>
																	</tbody>
																</table>
															</div>
															<div class="fc-content-skeleton">
																<table>
																	<thead>
																		<tr>
																			<td class="fc-day-top fc-sun fc-future"
																				data-date="2023-12-31"><span
																				class="fc-day-number">31</span></td>
																			<td
																				class="fc-day-top fc-mon fc-other-month fc-future"
																				data-date="2024-01-01"><span
																				class="fc-day-number">1</span></td>
																			<td
																				class="fc-day-top fc-tue fc-other-month fc-future"
																				data-date="2024-01-02"><span
																				class="fc-day-number">2</span></td>
																			<td
																				class="fc-day-top fc-wed fc-other-month fc-future"
																				data-date="2024-01-03"><span
																				class="fc-day-number">3</span></td>
																			<td
																				class="fc-day-top fc-thu fc-other-month fc-future"
																				data-date="2024-01-04"><span
																				class="fc-day-number">4</span></td>
																			<td
																				class="fc-day-top fc-fri fc-other-month fc-future"
																				data-date="2024-01-05"><span
																				class="fc-day-number">5</span></td>
																			<td
																				class="fc-day-top fc-sat fc-other-month fc-future"
																				data-date="2024-01-06"><span
																				class="fc-day-number">6</span></td>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
	</section>

	<script>
		// js 파일에 따로 넣으니까 적용이 안 됨ㅠㅠ
		const xValues = [ "Italy", "France", "Spain", "USA", "Argentina" ];
		const yValues = [ 55, 49, 44, 24, 15 ];

		new Chart("sales-chart", {
			type : "bar",
			data : {
				labels : xValues,
				datasets : [ {
					backgroundColor : "#a2c6a8",
					data : yValues
				} ]
			},
			options : {
				legend : {
					display : false
				},
				title : {
					display : true,
					text : "World Wine Production 2018"
				}
			}
		});
	</script>
	<script src="/resources/js/main/main.js"></script>

</body>
</html>