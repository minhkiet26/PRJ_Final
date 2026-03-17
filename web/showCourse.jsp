<%@page import="entities.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Courses</title>
        <link rel="stylesheet" href="showCourse.css"> 
    </head>
    <body>
        <jsp:include page="taskBar.jsp"/>

        <div class="user-list-container">
            <div class="top-controls">
                <button class="btn-add-circle" title="Thêm mới Course" onclick="openAddModal()">+</button>
                <div class="search-wrapper">
                    <jsp:include page="searchBar.jsp">
                        <jsp:param name="searchTarget" value="course"/>
                    </jsp:include>
                </div>
            </div>

            <h2 class="section-title">Course List</h2>
            <%
                ArrayList<Course> courseList = (ArrayList<Course>) request.getAttribute("LIST_COURSE");
                ArrayList<Course> courseListSearch = (ArrayList<Course>) request.getAttribute("LIST_COURSE_SEARCH");
                ArrayList<Course> listToDisplay = (courseListSearch != null) ? courseListSearch : courseList;
                if (listToDisplay != null && !listToDisplay.isEmpty()) {
                    for (Course c : listToDisplay) {
            %>
            <div class="user-item">
                <div class="user-bar">
                    <div class="user-email"><strong>[Course]</strong> <%= c.getCourseID()%> - <%= c.getCourseName()%></div>
                    <div class="action-buttons">
                        <button class="btn-edit" onclick="toggleEditPanel('edit-course-<%= c.getCourseID()%>')">Edit</button>
                        <% if ("Open".equals(c.getStatus())) {%>
                        <a href="#" onclick="confirmDelete('<%= c.getCourseID()%>')" class="btn-delete">Delete</a>
                        <% } %>
                        <% if ("Close".equals(c.getStatus())) {%>
                        <a href="#" onclick="confirmOpen('<%= c.getCourseID()%>')" class="btn-delete">Open</a>
                        <% }%>
                    </div>
                </div>

                <div id="edit-course-<%= c.getCourseID()%>" class="edit-panel">
                    <form action="UpdateCourseController" method="POST">
                        <div class="form-grid">
                            <div class="field">
                                <label>Course ID</label>
                                <input type="text" name="CourseID" value="<%= c.getCourseID()%>" readonly class="readonly-input">
                            </div>
                            <div class="field">
                                <label>Course Name</label>
                                <input type="text" name="CourseName" value="<%= c.getCourseName()%>">
                            </div>
                            <div class="field">
                                <label>Tuition Fee</label>
                                <input type="text" name="TuitionFee" value="<%= c.getTuitionFee()%>">
                            </div>
                            <div class="field">
                                <label>Teacher ID</label>
                                <input type="text" name="TeacherID" value="<%= c.getTeacherID()%>">
                            </div>
                            <div class="field">
                                <label>Study Time</label>
                                <input type="text" name="StudyTime" value="<%= c.getStudyTime()%>">
                            </div>
                            <div class="field">
                                <label>Schedule</label>
                                <input type="text" name="Schedule" value="<%= c.getSchedule()%>">
                            </div>
                            <div class="field">
                                <label>Start Date</label>
                                <input type="text" name="StartDate" value="<%= c.getStartDate()%>">
                            </div>
                            <div class="field">
                                <label>Total Lectures</label>
                                <input type="text" name="TotalLectures" value="<%= c.getTotalLectures()%>">
                            </div>
                            <div class="field">
                                <label>Number Enrolled</label>
                                <input type="text" name="NumberEnrolled" value="<%= c.getNumberEnrolled()%>">
                            </div>
                            <div class="field">
                                <label>Status</label>
                                <input type="text" name="Status" value="<%= c.getStatus()%>">
                            </div>
                            <div class="field full-width">
                                <label>Image URL</label>
                                <input type="text" name="ImageURL" value="<%= c.getImageURL()%>">
                            </div>
                            <div class="field full-width">
                                <label>Description</label>
                                <input type="text" name="Description" value="<%= c.getDescription()%>">
                            </div>
                        </div>
                        <button type="submit" class="btn-save" style="margin-top: 15px;">Lưu Course</button>
                    </form>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>

        <div id="addCourseModal" class="modal-overlay">
            <div class="modal-content" style="max-width: 700px;"> 
                <span class="close-btn" onclick="closeAddModal()">&times;</span>
                <div class="modal-header">Thêm Course Mới</div>

                <form action="AddCourseController" method="POST" class="signup" novalidate>
                    <div class="form-grid">
                        <div class="field">
                            <label>Course Name</label>
                            <input type="text" name="CourseName" required>
                            <span id="err-CourseName" style="color: red; font-size: 12px; display: block; margin-top: 3px;"></span>
                        </div>

                        <div class="field">
                            <label>Tuition Fee</label>
                            <input type="text" name="TuitionFee" required>
                            <span id="err-TuitionFee" style="color: red; font-size: 12px; display: block; margin-top: 3px;"></span>
                        </div>

                        <div class="field">
                            <label>Teacher ID</label>
                            <input type="text" name="TeacherID" required>
                            <span id="err-TeacherID" style="color: red; font-size: 12px; display: block; margin-top: 3px;"></span>
                        </div>

                        <div class="field">
                            <label>Study Time</label>
                            <input type="text" name="StudyTime" required>
                            <span id="err-StudyTime" style="color: red; font-size: 12px; display: block; margin-top: 3px;"></span>
                        </div>

                        <div class="field">
                            <label>Schedule</label>
                            <input type="text" name="Schedule" required>
                            <span id="err-Schedule" style="color: red; font-size: 12px; display: block; margin-top: 3px;"></span>
                        </div>

                        <div class="field">
                            <label>Start Date</label>
                            <input type="text" name="StartDate" placeholder="YYYY-MM-DD" required>
                            <span id="err-StartDate" style="color: red; font-size: 12px; display: block; margin-top: 3px;"></span>
                        </div>

                        <div class="field">
                            <label>Total Lectures</label>
                            <input type="text" name="TotalLectures" required>
                            <span id="err-TotalLectures" style="color: red; font-size: 12px; display: block; margin-top: 3px;"></span>
                        </div>

                        <div class="field">
                            <label>Number Enrolled (Mặc định)</label>
                            <input type="text" name="NumberEnrolled" value="0" readonly style="background-color: #e9ecef; cursor: not-allowed;">
                        </div>

                        <div class="field">
                            <label>Enrollment Status</label>
                            <input type="text" name="enrollmentStatus" value="Open" readonly style="background-color: #e9ecef; cursor: not-allowed;">
                        </div>

                        <div class="field full-width">
                            <label>Image URL</label>
                            <input type="text" name="ImageURL">
                        </div>

                        <div class="field full-width">
                            <label>Description</label>
                            <input type="text" name="Description">
                        </div>
                    </div>
                    <button type="submit" class="btn-submit-green" style="margin-top: 20px; width: 100%;">Tạo Course</button>
                </form>
            </div>
        </div>

        <script>
            function toggleEditPanel(panelId) {
                var panel = document.getElementById(panelId);
                panel.classList.toggle("show");
            }

            function openAddModal() {
                document.getElementById("addCourseModal").style.display = "flex";
            }

            function closeAddModal() {
                document.getElementById("addCourseModal").style.display = "none";
            }

            window.onclick = function (event) {
                var modal = document.getElementById("addCourseModal");
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            document.addEventListener("DOMContentLoaded", function () {
                const observer = new IntersectionObserver((entries) => {
                    entries.forEach(entry => {
                        if (entry.isIntersecting) {
                            entry.target.classList.add('show-on-scroll');
                        } else {
                            entry.target.classList.remove('show-on-scroll');
                        }
                    });
                }, {threshold: 0.1});

                document.querySelectorAll('.user-item').forEach(item => {
                    observer.observe(item);
                });
            });

            function confirmDelete(courseID) {
                if (confirm("Xác nhận xóa khóa học có ID: " + courseID + "?")) {
                    window.location.href = "DeleteCourseController?courseID=" + courseID;
                }
            }
            function confirmOpen(courseID) {
                if (confirm("Xác nhận mở lại khóa học có ID: " + courseID + "?")) {
                    window.location.href = "OpenCourseController?courseID=" + courseID;
                }
            }
        </script>

        <script src="checkCourseValid.js"></script>
        <c:if test="${not empty sessionScope.STATUS}">
            <script>
            alert("${sessionScope.STATUS}");
            </script>
            <c:remove var="STATUS" scope="session" />
        </c:if>
    </body>
</html>