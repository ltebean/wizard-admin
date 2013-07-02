var widgetOperation = function ($http) {
    //save the widget
    var commit = function (widget, author, comment, cb) {
        var requestBody = {
            comment: comment,
            author: author,
            widget: widget
        }
        $http.post("/api/widget/commit", requestBody)
            .success(function (data) {
                cb(data);
            }).error(function (data) {
                alert("server error");
            });
    }
    //load a widget
    var load = function (widgetName, cb) {
        $http.get("/api/widget/" + widgetName)
            .success(function (data) {
                cb(data);
            }).error(function (data) {
                alert("server error");
            });
    }

    var save=function(widget,cb){
        $http.post("/api/widget/", widget)
            .success(function (data) {
                cb(data);
            }).error(function (data) {
                alert("server error");
            });
    }
    //render a widget
    var render = function (paramScript, widget, mode, cb) {
        if (paramScript == undefined) {
            paramScript = '';
        }
        var body = {
            paramScript: paramScript,
            widget: widget
        }
        $http.post("/api/widget-output/" + mode, body)
            .success(function (data) {
                cb(data)
            }).error(function (data) {
                alert("server error");
            });
    }

    var loadHistory = function (widgetName, page, cb) {
        $http.get("/api/widget/" + widgetName + "/history?page=" + (page || 0))
            .success(function (data) {
                cb(data);
            }).error(function (data) {
                alert("server error");
            });
    }
    return {
        commit: commit,
        load: load,
        save:save,
        render: render,
        loadHistory: loadHistory
    };
}