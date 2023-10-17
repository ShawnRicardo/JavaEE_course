var vue = new Vue({
    el: '#app',
    data: {
        keyword: "",
        productMap: [], // 查询结果
        currentProduct: {}, // 当前编辑的商品
        dialogVisible: false, // 对话框是否显示
        editMode: false  // 当前是否是编辑模式（还是添加模式）
    },
    methods: {
        query: function (keyword) {
            var path = '/productMap';
            if (this.keyword != "") path = path + "?name=" + this.keyword;
            var self = this;
            axios.get(path)
                .then(response => self.productMap = response.data)
                .catch(e => self.$message.error(e.response.data));
        },
        resetProduct: function (product) {
            var self = this;
            axios.delete('/productMap/' + product.id)
                .then(response => self.query())
                .catch(e => self.$message.error(e.response.data));
        },
        showEdit: function (product) {
            this.dialogVisible = true;
            this.editMode = true;
            this.currentProduct = Object.assign({}, product);
        },
        showAdd: function () {
            this.dialogVisible = true;
            this.editMode = false;
            this.currentProduct = { available: false };
        },
        saveProduct: function () {
            var self = this;
            if (self.editMode) {
                axios.put('/productMap/' + self.currentProduct.id, self.currentProduct)
                    .then(response => self.query())
                    .catch(e => self.$message.error(e.response.data));
            } else {
                axios.post('/productMap', self.currentProduct)
                    .then(response => self.query())
                    .catch(e => self.$message.error(e.response.data));
            }
            this.dialogVisible = false;
        }
    }
});
vue.query();
