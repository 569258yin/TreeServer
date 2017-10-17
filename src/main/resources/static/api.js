function createTree(json) {
    $.ajax({
        url: "/api/tree",
        data: {tree: json},
        type: "POST",
        dataType: "JSON",
        success: function (data) {
            if (data.ret) {
                if (data.data != null) {
                    console.log(data.data)
                }
            }else {
                alert(data.message)
            }
        },
        error: function (data) {
            console.log(data.message)
        }
    })
}

function getAllTrees() {
    $.ajax({
        url: "/api/trees",
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            if (data.ret) {
                if (data.data != null) {
                    var treeData = data.data;
                    for(var i =0;i<treeData.length;i++) {
                        var id = treeData[i].id;
                        var treeJson = treeData[i].json;
                    }
                }
            }else {
                alert(data.message)
            }
        },
        error: function (data) {
            console.log(data.message)
        }
    })
}