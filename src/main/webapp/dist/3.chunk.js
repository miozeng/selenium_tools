webpackJsonp([3],{

/***/ "../../../../../src/app/layout/step-edit/step-edit-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__step_edit_component__ = __webpack_require__("../../../../../src/app/layout/step-edit/step-edit.component.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StepEditRoutingModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    { path: '', component: __WEBPACK_IMPORTED_MODULE_2__step_edit_component__["a" /* StepEditComponent */] }
];
var StepEditRoutingModule = (function () {
    function StepEditRoutingModule() {
    }
    return StepEditRoutingModule;
}());
StepEditRoutingModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        imports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */].forChild(routes)],
        exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */]]
    })
], StepEditRoutingModule);

//# sourceMappingURL=step-edit-routing.module.js.map

/***/ }),

/***/ "../../../../../src/app/layout/step-edit/step-edit.component.html":
/***/ (function(module, exports) {

module.exports = "<div [@routerTransition]>\n\t<app-page-header [heading]=\"'Forms'\" [icon]=\"'fa-edit'\"></app-page-header>\n\n\t<div class=\"row\">\n\t\t<div class=\"col-lg-12\">\n\n\t\t\t<form role=\"form\" novalidate (ngSubmit)=\"onSubmit()\" #f=\"ngForm\">\n\t\t\t<div class=\"row\">\n\t\t\t\t<fieldset class=\"form-group\" class=\"col-lg-6\">\n\t\t\t\t\t<label>Step Name</label>\n\t\t\t\t\t <input class=\"form-control\" [(ngModel)]=\"step.stepName\" name=\"stepName\"  #stepName=\"ngModel\" required >\n\t\t\t\t\t<div *ngIf=\"stepName.invalid && (stepName.dirty || stepName.touched)\" class=\"alert alert-danger\">\n\t\t\t\t\t\t<div *ngIf=\"stepName.errors.required\">Name is required.</div>\n\t\t\t\t\t</div>\n\t\t\t\t</fieldset>\n\t\t\t\t\t<fieldset class=\"form-group\"class=\"col-lg-6\">\n\t\t\t\t\t<label>Step No</label> \n\t\t\t\t\t<input class=\"form-control\" [(ngModel)]=\"step.stepNo\" name=\"stepNo\" #stepNo=\"ngModel\" required >\n\t\t\t\t\t<div *ngIf=\"stepNo.invalid && (stepNo.dirty || stepNo.touched)\" class=\"alert alert-danger\">\n\t\t\t\t\t\t<div *ngIf=\"stepNo.errors.required\">No is required.</div>\n\t\t\t\t\t</div>\n\t\t\t\t</fieldset>\n\t\t\t\n\t\t\t\t</div>\n\t\t\t\t<div class=\"row\">\n\t\t\t\t<fieldset class=\"form-group\" class=\"col-lg-10\">\n\t\t\t\t\t<label>Step Description</label> \n\t\t\t\t\t<textarea class=\"form-control\" [(ngModel)]=\"step.stepDesc\" name=\"stepDesc\" #stepDesc=\"ngModel\" required minlength=\"4\"></textarea>\n\t\t\t\t\t<div *ngIf=\"stepDesc.invalid && (stepDesc.dirty || stepDesc.touched)\" class=\"alert alert-danger\">\n\t\t\t\t\t\t<div *ngIf=\"stepDesc.errors.required\">Description is required.</div>\n\t\t\t\t\t\t<div *ngIf=\"stepDesc.errors.minlength\">Description must be at least 4 characters long.</div>\n\t\t\t\t\t</div>\n\t\t\t\t</fieldset>\n\t\t\t\t</div>\n\t\t\t\t<div class=\"row\">\n\t\t\t\t<fieldset class=\"form-group\" class=\"col-lg-6\">\n\t\t\t\t\t<label>Step Sequence</label> \n\t\t\t\t\t<input type=\"number\" class=\"form-control\" [(ngModel)]=\"step.sequence\" name=\"sequence\" #sequence=\"ngModel\" required >\n\t\t\t\t\t<div *ngIf=\"sequence.invalid && (sequence.dirty || sequence.touched)\" class=\"alert alert-danger\">\n\t\t\t\t\t\t<div *ngIf=\"sequence.errors.required\">Sequence is required.</div>\n\t\t\t\t\t</div>\n\t\t\t\t</fieldset>\n\t\t\t\n\t\t\t\t<fieldset class=\"form-group\" class=\"col-lg-6\">\n\t\t\t\t\t<label>Take Photo </label> <br/>\n\t\t\t\t  <input type=\"radio\"  name=\"takePhoto\" [(ngModel)]=\"step.takePhoto\" #takePhoto=\"ngModel\"  required [value]=\"true\" > Yes\n\t\t\t\t  <input type=\"radio\" name=\"takePhoto\" [(ngModel)]=\"step.takePhoto\" #takePhoto=\"ngModel\"  required [value]=\"false\" > No\n\t\t\t\t  <div *ngIf=\"takePhoto.invalid && (takePhoto.dirty || takePhoto.touched)\" class=\"alert alert-danger\">\n\t\t\t\t\t\t<div *ngIf=\"takePhoto.errors.required\">takePhoto is required.</div>\n\t\t\t\t  </div>\n\t\t\t\t</fieldset>\n\t\t\t\t</div>\n\t\t\t\t<div class=\"row\">\n\t\t\t\t<fieldset class=\"form-group\"  class=\"col-lg-6\">\n\t\t\t\t\t<label>Get Type</label> \n\t\t\t\t <select class=\"form-control\" name=\"getType\" [(ngModel)]=\"step.getType\"   #getType=\"ngModel\" >\n\t\t\t        <option value=\"byId\"> byId</option>\n\t\t\t        <option value=\"byClass\"> byClass</option>\n\t\t\t        <option value=\"byTag\"> byTag</option>\n\t\t\t        <option value=\"byName\"> byName</option>\n\t\t\t        <option value=\"byLink\"> byLink</option>\n\t\t\t        <option value=\"byPartialLink\"> byPartialLink</option>\n\t\t\t        <option value=\"byCss\"> byCss</option>\n\t\t\t        <option value=\"byXpath\"> byXpath</option>\n\t\t\t        <option value=\"Alert\"> Alert</option>\n\t\t\t      </select>\n\t\t\t        \n\t\t\t\t</fieldset>\n\t\t\t\t<fieldset class=\"form-group\"  class=\"col-lg-6\">\n\t\t\t\t\t<label>url</label> \n\t\t\t\t    <input  type=\"url\" class=\"form-control\" [(ngModel)]=\"step.url\" #url=\"ngModel\" name=\"url\">\n\t\t\t\t    <div *ngIf=\"url.invalid && (url.dirty || url.touched)\" class=\"alert alert-danger\">\n\t\t\t\t\t\t<div *ngIf=\"url.errors.url\">invalid</div>\n\t\t\t\t   </div>\n\t\t\t\t</fieldset>\n\t\t\t\t</div>\n\t\t\t\t\t<div class=\"row\">\n\t\t\t\t<fieldset class=\"form-group\"  class=\"col-lg-6\">\n\t\t\t\t\t<label>Element Name</label> \n\t\t\t\t    <input class=\"form-control\" [(ngModel)]=\"step.elementName\" name=\"elementName\" #elementName=\"ngModel\">\n\t\t\t\t</fieldset>\n\t\t\t\t<fieldset class=\"form-group\"  class=\"col-lg-6\">\n\t\t\t\t\t<label>Element Value</label> \n\t\t\t\t    <input class=\"form-control\" [(ngModel)]=\"step.elementValue\" name=\"elementValue\" #elementValue=\"ngModel\">\n\t\t\t\t</fieldset>\n\t\t\t\t<fieldset class=\"form-group\"  class=\"col-lg-6\">\n\t\t\t\t\t<label>Element Sequence</label> \n\t\t\t\t    <input type=\"number\"  class=\"form-control\" [(ngModel)]=\"step.elementSeq\" name=\"elementSeq\" #elementValue=\"ngModel\">\n\t\t\t\t</fieldset>\n\t\t\t\t<fieldset class=\"form-group\"  class=\"col-lg-6\">\n\t\t\t\t\t<label>waitTime</label> \n\t\t\t\t    <input type=\"number\"  class=\"form-control\" [(ngModel)]=\"step.waitTime\" name=\"waitTime\" #elementValue=\"ngModel\">\n\t\t\t\t</fieldset>\n\t\t\t\t</div>\n\t\t\t\t<div class=\"row\">\n\t\t\t\t<fieldset class=\"form-group\" class=\"col-lg-6\">\n\t\t\t\t\t<label>Do Type</label> \n\t\t\t\t <select class=\"form-control\" name=\"doType\" [(ngModel)]=\"step.doType\" #doType=\"ngModel\" required>\n\t\t\t        <option value=\"FetchingPage\"> FetchingPage</option>\n\t\t\t        <option value=\"getText\"> getText</option>\n\t\t\t        <option value=\"click\"> click</option>\n\t\t\t        <option value=\"sendKeys\"> sendKeys</option>\n\t\t\t        <option value=\"clear\"> clear</option>\n\t\t\t        <option value=\"selectByIndex\"> selectByIndex</option>\n\t\t\t        <option value=\"selectByVisibleText\"> selectByVisibleText</option>\n\t\t\t        <option value=\"selectByValue\"> selectByValue</option>\n\t\t\t        <option value=\"isSelected\"> isSelected</option>\n\t\t\t        <option value=\"isEnabled\"> isEnabled</option>\n\t\t\t        <option value=\"isDisplayed\"> isDisplayed</option>\n\t\t\t        <option value=\"noThing\"> noThing</option>\n\t\t\t        <option value=\"accept\"> accept</option>\n\t\t\t        <option value=\"dismiss\"> dismiss</option>\n\t\t\t      </select>\n\t\t\t       <div *ngIf=\"doType.invalid && (doType.dirty || doType.touched)\" class=\"alert alert-danger\">\n\t\t\t\t\t\t<div *ngIf=\"doType.errors.required\">doType is required.</div>\n\t\t\t\t  </div>\n\t\t\t\t</fieldset>\n\t\t\t\t<fieldset class=\"form-group\" class=\"col-lg-6\">\n\t\t\t\t\t<label>Except Value</label> \n\t\t\t\t\t<input class=\"form-control\" [(ngModel)]=\"step.excptValue\" name=\"excptValue\" #excptValue=\"ngModel\">\n\t\t\t\t</fieldset>\n\t\t\t\t</div>\n\t\t\t\t<div class=\"row\" class=\"form-group\" style=\"margin-top: 10px;text-align: center;\">\n\t\t\t\t<button type=\"submit\" [disabled]=\"!f.form.valid\"\n\t\t\t\t\tclass=\"btn btn-secondary\">Submit</button>\n\t\t\t\t<button type=\"reset\" class=\"btn btn-secondary\">Reset Button</button>\n\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" (click)=\"back()\">Back</button>\n\t\t\t\t</div>\n\t\t\t</form>\n\n\t\t</div>\n\n\t</div>\n\t<!-- /.row -->\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/layout/step-edit/step-edit.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/layout/step-edit/step-edit.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__router_animations__ = __webpack_require__("../../../../../src/app/router.animations.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__layout_service__ = __webpack_require__("../../../../../src/app/layout/layout.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__step_step__ = __webpack_require__("../../../../../src/app/layout/step/step.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__case_case__ = __webpack_require__("../../../../../src/app/layout/case/case.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StepEditComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var StepEditComponent = (function () {
    function StepEditComponent(layoutService, route, router) {
        this.layoutService = layoutService;
        this.route = route;
        this.router = router;
        this.step = new __WEBPACK_IMPORTED_MODULE_4__step_step__["a" /* Step */]();
        this.case = new __WEBPACK_IMPORTED_MODULE_5__case_case__["a" /* Case */]();
    }
    StepEditComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) { return _this.stepId = params.stepId; });
        this.route.params.subscribe(function (params) { return _this.caseId = params.caseId; });
        if (this.stepId != 0) {
            this.layoutService.get_step_byid(this.stepId).subscribe(function (res) {
                _this.step = res;
            });
        }
        else {
            this.layoutService.get_maxSeq_byid(this.caseId).subscribe(function (res) {
                _this.step.sequence = res;
            });
            this.case.caseId = this.caseId;
            this.step.testCase = this.case;
        }
    };
    StepEditComponent.prototype.onSubmit = function () {
        var _this = this;
        this.layoutService.post_step(this.step).subscribe(function (val) {
            console.log("POST call successful value returned in body", val);
        }, function (response) {
            alert("save module in error");
        }, function () {
            console.log("The POST observable is now completed.");
            //window.location.href = "/liststep/"+this.caseId;
            _this.router.navigate(['liststep/' + _this.caseId]);
        });
    };
    StepEditComponent.prototype.back = function () {
        // window.location.href = "/liststep/"+this.caseId;
        this.router.navigate(['liststep/' + this.caseId]);
    };
    return StepEditComponent;
}());
StepEditComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-module-edit',
        template: __webpack_require__("../../../../../src/app/layout/step-edit/step-edit.component.html"),
        styles: [__webpack_require__("../../../../../src/app/layout/step-edit/step-edit.component.scss")],
        animations: [__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__router_animations__["a" /* routerTransition */])()]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__layout_service__["a" /* LayoutService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__layout_service__["a" /* LayoutService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["b" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["b" /* Router */]) === "function" && _c || Object])
], StepEditComponent);

var _a, _b, _c;
//# sourceMappingURL=step-edit.component.js.map

/***/ }),

/***/ "../../../../../src/app/layout/step-edit/step-edit.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/@angular/common.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_common_http__ = __webpack_require__("../../../common/@angular/common/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__shared_interceptor_http_interceptor__ = __webpack_require__("../../../../../src/app/shared/interceptor/http.interceptor.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__step_edit_routing_module__ = __webpack_require__("../../../../../src/app/layout/step-edit/step-edit-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__step_edit_component__ = __webpack_require__("../../../../../src/app/layout/step-edit/step-edit.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__shared__ = __webpack_require__("../../../../../src/app/shared/index.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__layout_service__ = __webpack_require__("../../../../../src/app/layout/layout.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "StepEditModule", function() { return StepEditModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};









var StepEditModule = (function () {
    function StepEditModule() {
    }
    return StepEditModule;
}());
StepEditModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        imports: [
            __WEBPACK_IMPORTED_MODULE_1__angular_common__["CommonModule"],
            __WEBPACK_IMPORTED_MODULE_5__step_edit_routing_module__["a" /* StepEditRoutingModule */],
            __WEBPACK_IMPORTED_MODULE_7__shared__["b" /* PageHeaderModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* ReactiveFormsModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
            __WEBPACK_IMPORTED_MODULE_3__angular_common_http__["a" /* HttpClientModule */]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_8__layout_service__["a" /* LayoutService */],
            { provide: __WEBPACK_IMPORTED_MODULE_3__angular_common_http__["b" /* HTTP_INTERCEPTORS */], useClass: __WEBPACK_IMPORTED_MODULE_4__shared_interceptor_http_interceptor__["a" /* MyHttpLogInterceptor */], multi: true }],
        declarations: [__WEBPACK_IMPORTED_MODULE_6__step_edit_component__["a" /* StepEditComponent */]]
    })
], StepEditModule);

//# sourceMappingURL=step-edit.module.js.map

/***/ }),

/***/ "../../../../../src/app/layout/step/step.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Step; });
var Step = (function () {
    function Step() {
    }
    return Step;
}());

//# sourceMappingURL=step.js.map

/***/ })

});
//# sourceMappingURL=3.chunk.js.map