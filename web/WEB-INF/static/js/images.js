/**
 * Created by 墨盒 on 2017/8/16.
 * 图片页面专用js
 */
$(function () {
    layer.photos({
        photos: '.image_item>.image'
    });
    loadLazy('.image_item>.image>img');
});
function loadImageMenu(path) {
    loadMenu({
        path:path,
        owner: '.image_item',
        menu: [
            {
                value: '编辑',
                callback: function () {
                    alert(1);
                }
            },
            {
                value: '删除',
                callback: function ($menu) {
                    layerConfirm('您确定删除该图片？', function () {
                        var id = $menu.parent().parent().find('.image>img').attr('src').match(/\/([1-9]*[0-9]+)\/res/)[1];
                        if (id === null || typeof (id) === 'undefined') {
                            show('错误数据', '刷新页面', function () {
                                location.reload();
                            });
                            return;
                        }
                        ajax({
                            url: id + '/removeImg',
                            type: 'get',
                            dataType: 'json',
                            success: function (result) {
                                if (result.status === 0) {
                                    $menu.parent().parent().remove();
                                    show('删除图片成功');
                                } else if (result.status === 1) {
                                    show('删除图片失败');
                                }
                                // ajaxHandler(result, [
                                //     {
                                //         key: 0, callback: function () {
                                //         $menu.parent().parent().remove();
                                //         show('删除图片成功');
                                //     }
                                //     },
                                //     {
                                //         key: 1, callback: function () {
                                //         show('删除图片失败');
                                //     }
                                //     }]);

                            }
                        })
                    });
                }
            },
            {
                value: '设为封面',
                callback: function ($menu) {
                    layerConfirm('您确定将该图片设置成封面？', function () {
                        var id = $menu.parent().parent().find('.image>img').attr('src').match(/\/([1-9]*[0-9]+)\/res/)[1];
                        if (id === null || typeof (id) === 'undefined') {
                            show('错误数据', '刷新页面', function () {
                                location.reload();
                            });
                            return;
                        }
                        ajax({
                            url: id + '/setCover',
                            type: 'get',
                            dataType: 'json',
                            success: function (result) {
                                if (result.status === 0) {
                                    show('设置封面成功', null, null);
                                } else if (result.status === 1) {
                                    show('设置封面成功', null, null);
                                }
                                // ajaxHandler(result, [
                                //     {
                                //         key: 0, callback: function () {
                                //         show('设置封面成功', null, null);
                                //     }
                                //     },
                                //     {
                                //         key: 1, callback: function () {
                                //         show('设置封面成功', null, null);
                                //     }
                                //     }]);
                            }
                        });
                    }, function () {

                    });
                }
            }
        ]
    });
}
function loadImageManager(uploadUrl) {
    var imageIds = [];
    var index;
    var count = 0;
    loadManger({
        id: '.image_group'
        , images: '.image'
        , cancel: function () {
            layer.photos({
                photos: '.image_item>.image'
            });
        }
        , upload: function () {
            //页面层
            loadUpload('../', uploadUrl);
        }, create: function () {
            layer.open({
                type: 1,
                shade: false,
                title: '创建相册', //不显示标题
                content: $('#createTable') //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            });
        }, remove: function (images) {
            layerConfirm('确认删除' + images.length + "张图片？", function () {
                var $body = $('body:eq(0)');
                imageIds = [];
                for (var i = 0; i < images.length; i++) {
                    index = i;
                    var imageId = getId(images[i].find('img').attr('lazy'), 2);
                    if (imageId !== -1) {
                        imageIds.push(imageId);
                        var fun = function () {
                            index--;
                            ajax({
                                url: imageIds[count] + '/removeImg',
                                dataType: 'json',
                                success: function () {
                                    count++;
                                    console.log('count = ' + count + "  index = " + index + "   " + images.length);
                                    if (index < 0) {
                                        layer.closeAll();
                                        show(count + '张图片删除成功', '刷新页面', function () {
                                            location.reload();
                                        })
                                    } else
                                        $body.dequeue('remove');
                                }
                            })
                        };
                        fun.imageId = imageId;
                        $body.queue('remove', fun);
                    }
                }
                $body.dequeue('remove')
                layer.load({type: 1});
            });
        }
    });

}