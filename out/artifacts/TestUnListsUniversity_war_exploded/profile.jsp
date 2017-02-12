<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Student Profile</title>

    <jsp:include page="included_pages/cssAndJs.jsp"/>

</head>

<body>

<jsp:include page="included_pages/fixedTopNavbar.jsp"/>
<jsp:include page="included_pages/headerJumbotronProfile.jsp"/>

<!-- Body -->
<div class="container">
    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-2">
            <!-- Empty DIV -->
            <div style="padding: 10px"></div>

            <!-- Profile photo -->
            <div class="center-block">
                <img src="images/photo.jpg" class="img-responsive img-thumbnail" alt="Responsive image">
            </div>

            <!-- Empty DIV -->
            <div style="padding: 10px"></div>

            <!-- Buttons area -->
            <button type = "button" class = "btn btn-default btn-sm btn-block">
                Edit
            </button>
        </div>
        <div class="col-sm-8">
            <!-- Empty DIV -->
            <div style="padding: 10px"></div>
            <!-- Information -->
            <div class="container">
                <!-- University information -->
                <table class="table table-condensed font-table">
                    <thead>
                    <tr>
                        <th class="col-sm-2">Parameter</th>
                        <th class="col-sm-6">University Information</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Full name:</td>
                        <td class="col-sm-6"><i>Dimitry Miedviediev Michailovich</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Entry date:</td>
                        <td class="col-sm-6"><i>31 AUG 2014</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Status:</td>
                        <td class="col-sm-6"><i>Educate</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Speciality:</td>
                        <td class="col-sm-6"><i>Mechanic</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Group/Subgroup:</td>
                        <td class="col-sm-6"><i>2/1</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Qualification level:</td>
                        <td class="col-sm-6"><i>Bachelor</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Course:</td>
                        <td class="col-sm-6"><i>4</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Education form:</td>
                        <td class="col-sm-6"><i>Full time</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Financing:</td>
                        <td class="col-sm-6"><i>Government</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Stud book:</td>
                        <td class="col-sm-6"><i>16.342-42</i></td>
                    </tr>
                    </tbody>
                </table>
                <!-- Student information -->
                <table class="table table-condensed font-table">
                    <thead>
                    <tr>
                        <th class="col-sm-2">Parameter</th>
                        <th class="col-sm-6">Student Information</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Birth date:</td>
                        <td class="col-sm-6"><i>20 OCT 1995</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Passport serial:</td>
                        <td class="col-sm-6"><i>HB 673452</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Passport office:</td>
                        <td class="col-sm-6"><i>Chmelnitsky UNVSU</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Passport date-release:</td>
                        <td class="col-sm-6"><i>24 JUN 2011</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Identification code:</td>
                        <td class="col-sm-6"><i>4563453578764323</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Address:</td>
                        <td class="col-sm-6"><i>Kniaziv-Koriatovichiv st. 17/3</i></td>
                    </tr>
                    </tbody>
                </table>
                <!-- Parents information -->
                <table class="table table-condensed font-table">
                    <thead>
                    <tr>
                        <th class="col-sm-2">Parameter</th>
                        <th class="col-sm-6">Parents Information</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Father full-name:</td>
                        <td class="col-sm-6"><i>Michailo Miedviediev Volodimirovich</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Address:</td>
                        <td class="col-sm-6"><i>......</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">First phone:</td>
                        <td class="col-sm-6"><i>+38 (098) 454-43-34</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Second phone:</td>
                        <td class="col-sm-6"><i>+38 (098) 343-11-90</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Mother full-name:</td>
                        <td class="col-sm-6"><i>Maria Miedviedieva Valeryivna</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Address:</td>
                        <td class="col-sm-6"><i>Kniaziv-Koriatovichiv st. 17/3</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">First phone:</td>
                        <td class="col-sm-6"><i>+38 (097) 251-44-69</i></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td class="col-sm-2">Second phone:</td>
                        <td class="col-sm-6"><i>+38 (050) 195-45-72</i></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-sm-1"></div>
    </div>
</div>

<jsp:include page="included_pages/footerJumbotron.jsp"/>

</body>
</html>

