
module.exports = function () {
    /*
     var mongoose = require('mongoose');
     mongoose.connect('mongodb://localhost/test');
     */
    /*var mongoose = require('mongoose');
    mongoose.connect('mongodb://localhost/project-fall-2016');
*/
    var userModel = require("./user/user.model.server")();
    var favoriteModel = require("./favorite/favorite.model.server")();
//    var reviewModel = require("./reviews/review.model.server")();
//    var movieModel = require("./movie/movie.model.server")();
//    var followingModel = require("./following/following.model.server")();
//    var websiteModel = require("./website/website.model.server")();
//    var pageModel = require("./page/page.model.server")();
 //   var widgetModel = require("./widget/widget.model.server")();


    var models = {
        userModel: userModel,
        favoriteModel:favoriteModel,
        //reviewModel:reviewModel,
        //movieModel:movieModel,
        //followingModel:followingModel
        //websiteModel: websiteModel,
        //pageModel:pageModel,
        //widgetModel:widgetModel
    };

    /*websiteModel.setModel(models);
     userModel.setModel(models);
     pageModel.setModel(models);
     widgetModel.setModel(models);
     */
    return models;
};
