/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
Page.onReady = function() {
    /*
     * variables can be accessed through 'Page.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Page.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */

    Page.Widgets.EmployeeList1.selectfirstitem = true;


    var chat = {
        messages: [{
            name: "viki",
            message: "hi"
        }, {
            name: "Bot",
            message: "hi,how are you?"
        }, {
            name: "viki",
            message: "i am fine,how about you?"
        }, {
            name: "Bot",
            message: "Yeah,i also fine"
        }, {
            name: "Bot",
            message: "what can i do for you?"
        }]
    };
    var tempname = "";
    var obj = JSON.stringify(chat);
    var jsob = JSON.parse(obj);
    for (var i in jsob.messages) {
        if (jsob.messages[i].name === tempname) {
            document.getElementById('chat area').innerHTML += '<p>' + jsob.messages[i].message + "</p>";
        } else {
            document.getElementById('chat area').innerHTML += "<br>";
            document.getElementById('chat area').innerHTML += '<p>' + jsob.messages[i].name + "</p><br><p>" + jsob.messages[i].message + "</p>";
            tempname = jsob.messages[i].name;
        }
    }

    Page.Widgets.Name.caption = jsob.messages.name;
    Page.Widgets.content.caption = jsob.messages.message;
};
Page.popover3Show = function($event, widget) {
    var d, h, m;

    Page.Widgets.popover3.content = "1";
    d = new Date();

    h = d.getHours();
    m = d.getMinutes();
    var ampm = h >= 12 ? 'pm' : 'am';


    m = m + 30;
    if (m > 60) {
        h++;
        m = m - 60;
    }

    ampm = h >= 12 ? 'pm' : 'am';
    h = h % 12;
    h = h ? h : 12;
    m = m < 10 ? '0' + m : m;
    Page.Widgets.label8.caption += " " + h + ":" + m + " " + ampm;

    h = d.getHours();
    m = d.getMinutes();
    h++;
    ampm = h >= 12 ? 'pm' : 'am';
    h = h % 12;
    h = h ? h : 12;
    m = m < 10 ? '0' + m : m;
    document.getElementsByClassName('min2').innerHTML += " " + h + ":" + m + " " + ampm;
    h++;
    ampm = h >= 12 ? 'pm' : 'am';
    h = h % 12;
    h = h ? h : 12;
    m = m < 10 ? '0' + m : m;
    document.getElementsByClassName('min3').innerHTML += " " + h + ":" + m + " " + ampm;
    h += 2;
    ampm = h >= 12 ? 'pm' : 'am';
    h = h % 12;
    h = h ? h : 12;
    m = m < 10 ? '0' + m : m;
    document.getElementsByClassName('min4').innerHTML += " " + h + ":" + m + " " + ampm;
    h += 4;
    ampm = h >= 12 ? 'pm' : 'am';
    h = h % 12;
    h = h ? h : 12;
    m = m < 10 ? '0' + m : m;
    document.getElementsByClassName('min5').innerHTML += " " + h + ":" + m + " " + ampm;


};
Page.popover2Show = function($event, widget) {
    document.getElementsByClassName('consts').style = " color:#00796b; background-color:#e4f7fb;border-top-right-radius:10px;border-bottom-right-radius:10px;border-top-left-radius:10px;border-bottom-left-radius:10px;";
};
Page.EmployeeList1Beforedatarender = function(widget, $data) {

};