var device = {
    getValue: function () {
        return delegate.getValue() * 100;
    },
    getLabel: function () {
        return 'decorated ' + delegate.getLabel();
    }
};

