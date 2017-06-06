var appCamera = {
  initialize: function() {
    var cameraElement = $("#question_camera");
    cameraElement.on('click', function(){
      //make call to camera
      console.log("hello");
      navigator.camera.getPicture(function() {
        console.log("success camera");
      }, function() {
        console.log("error camera");
      }, {});
    });
  }
}

appCamera.initialize();
