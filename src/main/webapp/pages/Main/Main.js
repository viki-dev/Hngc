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