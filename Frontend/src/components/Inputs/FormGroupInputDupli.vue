<template>
  <div class="form-group" :class="[{ 'input-group': hasIcon }]">
    <slot name="addonLeft">
      <div v-if="addonLeftIcon" class="input-group-prepend">
        <i class="input-group-text" :class="addonLeftIcon"></i>
      </div>
    </slot>
    <slot>
      <input
        :value="value"
        v-on="listeners"
        v-bind="$attrs"
        class="form-control"
        :class="[{ valid: value && !error }, inputClasses]"
        aria-describedby="addon-right addon-left"
        style="border-radius: 30px" />
    </slot>
    <button class="btn-round btn-warning">중복검사</button>
  </div>
</template>
<script>
export default {
  name: "fg-input-custom-dupli",
  props: {
    required: Boolean,
    label: String,
    error: String,
    labelClasses: String,
    inputClasses: String,
    value: {
      type: [String, Number],
      default: "",
    },
    addonRightIcon: String,
    addonLeftIcon: String,
  },
  data() {
    return {
      focused: false,
    };
  },
  computed: {
    listeners() {
      return {
        ...this.$listeners,
        input: this.updateValue,
        focus: this.onFocus,
        blur: this.onBlur,
      };
    },
    hasIcon() {
      const { addonRight, addonLeft } = this.$slots;
      return (
        addonRight !== undefined ||
        addonLeft !== undefined ||
        this.addonRightIcon !== undefined ||
        this.addonLeftIcon !== undefined
      );
    },
  },
  methods: {
    updateValue(evt) {
      let value = evt.target.value;
      this.$emit("input", value);
    },
    onFocus(value) {
      this.focused = true;
      this.$emit("focus", value);
    },
    onBlur(value) {
      this.focused = false;
      this.$emit("blur", value);
    },
  },
};
</script>
<style></style>
